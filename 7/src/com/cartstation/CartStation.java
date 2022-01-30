package com.cartstation;

import com.cartstation.interfaces.ICartStationListener;
import com.cartstation.interfaces.ICartStationService;
import com.controlcenter.common.MessageType;
import shoppingCarts.data.UpdateInfo;
import shoppingCarts.interfaces.IInfo;
import shoppingCarts.interfaces.IRegistration;
import shoppingCarts.interfaces.IUpdate;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

public class CartStation implements IInfo, ICartStationService {
    private final List<ICartStationListener> cartStationListeners;
    private String name;
    private int maxPlaces;
    private boolean isConnected = false;
    private int serverPort;
    private int currentPlacesNumber;

    public CartStation() {
        cartStationListeners = new LinkedList<>();
        try {
            UnicastRemoteObject.exportObject(this, 0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getName() throws RemoteException {
        return MessageType.CartStation + MessageType.Separator + name + MessageType.Separator + maxPlaces;
    }

    @Override
    public void addListener(ICartStationListener cartStationListener) {
        cartStationListeners.add(cartStationListener);
    }

    @Override
    public boolean handleConnection(String name, String portAsString, String maxPlacesAsString) {
        if (!validateData(name, portAsString, maxPlacesAsString)){
            cartStationListeners.forEach(x -> x.displayMessage("Invalid data"));
            return false;
        }
        this.serverPort = Integer.parseInt(portAsString);
        this.maxPlaces = Integer.parseInt(maxPlacesAsString);
        this.currentPlacesNumber = 0;
        this.name = name;
        try {
            registerCartStation();
            return true;
        } catch (RemoteException | NotBoundException e){
            e.printStackTrace();
            cartStationListeners.forEach(x -> x.displayMessage("Unable to reach server"));
        }
        return false;
    }

    @Override
    public boolean handleDisconnect() {
        if (!isConnected){
            return false;
        }
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", serverPort);
            Object server = registry.lookup("ControlCenter");
            if (server instanceof IRegistration){
                var registration = (IRegistration) server;
                registration.unregister(this);
                isConnected = false;
                return true;
            }
        } catch (RemoteException | NotBoundException e){
            cartStationListeners.forEach(x -> x.displayMessage("Unable to reach server"));
        }
        return false;
    }

    @Override
    public void takePlace() {
        updateCartStation(1);
    }

    @Override
    public void freePlaces() {
        updateCartStation(-currentPlacesNumber);
    }

    private void updateCartStation(int number){
        currentPlacesNumber += number;
        Registry registry = null;
        try {
            registry = LocateRegistry.getRegistry("localhost", serverPort);
            Object server = registry.lookup("ControlCenter");
            if (server instanceof IUpdate){
                var update = (IUpdate) server;
                var updateInfo = new UpdateInfo();
                updateInfo.stationName = name;
                updateInfo.capacity = maxPlaces;
                updateInfo.occupancy = currentPlacesNumber;
                update.update(updateInfo);
                cartStationListeners.forEach(e -> e.updateCartStation(currentPlacesNumber, maxPlaces));
            }
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    private boolean validateData(String name, String portAsString, String maxPlacesAsString){
        try {
            var portAsInt = Integer.parseInt(portAsString);
            var maxPlacesAsInt = Integer.parseInt(maxPlacesAsString);
            if (portAsInt < 1025 || portAsInt > 42000 || maxPlacesAsInt < 1){
                return false;
            }
            return !name.isEmpty() && !name.isBlank();
        } catch (NumberFormatException e){
            return false;
        }
    }

    private void registerCartStation() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("localhost", serverPort);
        Object server = registry.lookup("ControlCenter");
        if (server instanceof IRegistration){
            var registration = (IRegistration) server;
            registration.register(this);
            isConnected = true;
            cartStationListeners.forEach(e -> e.updateCartStation(currentPlacesNumber, maxPlaces));
        }
    }
}
