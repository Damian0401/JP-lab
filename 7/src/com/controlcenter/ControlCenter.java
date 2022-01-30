package com.controlcenter;

import com.controlcenter.common.MessageType;
import com.controlcenter.interfaces.IControlCenterListener;
import com.controlcenter.interfaces.IControlCenterService;
import shoppingCarts.data.UpdateInfo;
import shoppingCarts.interfaces.IInfo;
import shoppingCarts.interfaces.INotification;
import shoppingCarts.interfaces.IRegistration;
import shoppingCarts.interfaces.IUpdate;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

public class ControlCenter implements IUpdate, IRegistration, IControlCenterService {
    private final List<String> workers;
    private final List<UpdateInfo> cartStations;
    private final List<IControlCenterListener> controlCenterListeners;
    private boolean isConnected = false;
    private Registry registry;

    public ControlCenter() throws RemoteException {
        controlCenterListeners = new LinkedList<>();
        workers = new LinkedList<>();
        cartStations = new LinkedList<>();
        UnicastRemoteObject.exportObject(this, 0);
    }

    @Override
    public boolean register(IInfo info) throws RemoteException {
        var data = info.getName().split(MessageType.Separator);
        switch (data[0]){
            case MessageType.CartStation:
                if (cartStations.stream().anyMatch(x -> x.stationName.equals(data[1]))){
                    return false;
                }
                var updateInfo = new UpdateInfo();
                updateInfo.stationName = data[1];
                updateInfo.capacity = Integer.parseInt(data[2]);
                controlCenterListeners.forEach(e -> e.displayCartStation(updateInfo));
                cartStations.add(updateInfo);
                return true;
            case MessageType.Worker:
                if (workers.contains(data[1])){
                    return false;
                }
                controlCenterListeners.forEach(e -> e.displayWorker(data[1]));
                workers.add(data[1]);
                try {
                    registry.bind(data[1], info);
                } catch (AlreadyBoundException e) {
                    e.printStackTrace();
                }
                return true;
        }
        return false;
    }

    @Override
    public boolean unregister(IInfo info) throws RemoteException {
        var data = info.getName().split(MessageType.Separator);
        switch (data[0]){
            case MessageType.CartStation:
                controlCenterListeners.forEach(e -> e.removeCartStation(data[1]));
                cartStations.removeIf(x -> x.stationName.equals(data[1]));
                return true;
            case MessageType.Worker:
                controlCenterListeners.forEach(e -> e.removeWorker(data[1]));
                workers.remove(data[1]);
                try {
                    registry.unbind(data[1]);
                } catch (NotBoundException e) {
                    e.printStackTrace();
                }
                return true;
        }
        return false;
    }

    @Override
    public void update(UpdateInfo ui) throws RemoteException {
        if (ui.occupancy >= ui.capacity){
            sendNotification(ui);
        }
        controlCenterListeners.forEach(e -> e.updateCartStation(ui));
    }

    @Override
    public void addListener(IControlCenterListener controlCenterListener) {
        controlCenterListeners.add(controlCenterListener);
    }

    @Override
    public void handleConnection(String portAsString) {
        int portAsInt;
        try {
            portAsInt = Integer.parseInt(portAsString);
        } catch (NumberFormatException e){
            controlCenterListeners.forEach(x -> x.displayMessage("Invalid port number"));
            return;
        }
        if (isConnected){
            controlCenterListeners.forEach(x -> x.displayMessage("Already connected"));
            return;
        }
        if (portAsInt < 1024 || portAsInt > 42000){
            controlCenterListeners.forEach(x -> x.displayMessage("Invalid port number"));
            return;
        }
        isConnected = true;
        controlCenterListeners.forEach(x -> x.changeStatus("online / " + portAsString));
        try {
            registry = LocateRegistry.createRegistry(portAsInt);
            registry.bind("ControlCenter", this);
        } catch (RemoteException | AlreadyBoundException e) {
            controlCenterListeners.forEach(x -> x.displayMessage("Unable to launch"));
        }
    }

    private void sendNotification(UpdateInfo updateInfo){
        INotification notification = null;
        for (var workerName : workers){
            try {
                notification = (INotification)registry.lookup(workerName);
                notification.notify(updateInfo);
            } catch (RemoteException | NotBoundException e) {
                e.printStackTrace();
            }
        }
    }
}
