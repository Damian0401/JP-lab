package com.worker;

import com.controlcenter.common.MessageType;
import com.worker.interfaces.IWorkerListener;
import com.worker.interfaces.IWorkerService;
import shoppingCarts.data.UpdateInfo;
import shoppingCarts.interfaces.IInfo;
import shoppingCarts.interfaces.INotification;
import shoppingCarts.interfaces.IRegistration;

import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

public class Worker implements INotification, IInfo, IWorkerService, Serializable {
    private final static long serialVersionUID = 1L;
    private final List<IWorkerListener> workerListeners;
    private String name;
    private boolean isConnected = false;
    private int serverPort;

    public Worker() {
        workerListeners = new LinkedList<>();
        try {
            UnicastRemoteObject.exportObject(this, 0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getName() throws RemoteException {
        return MessageType.Worker + MessageType.Separator + name;
    }

    @Override
    public void notify(UpdateInfo ui) throws RemoteException {
        var notification = "[ " + ui.stationName + " - " + ui.occupancy + " - " + ui.capacity + " ]";
        workerListeners.forEach(e -> e.displayNotification(notification));
    }

    @Override
    public void addListener(IWorkerListener workerListener) {
        workerListeners.add(workerListener);
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
            workerListeners.forEach(x -> x.displayMessage("Unable to reach server"));
        }
        return false;
    }

    @Override
    public boolean handleConnection(String name, String portAsString) {
        if (!validateData(name, portAsString)){
            workerListeners.forEach(x -> x.displayMessage("Invalid data"));
            return false;
        }
        this.serverPort = Integer.parseInt(portAsString);
        this.name = name;
        try {
            registerWorker();
            return true;
        } catch (RemoteException | NotBoundException e){
            e.printStackTrace();
            workerListeners.forEach(x -> x.displayMessage("Unable to reach server"));
        }
        return false;
    }

    private boolean validateData(String name, String portAsString){
        try {
            var portAsInt = Integer.parseInt(portAsString);
            if (portAsInt < 1025 || portAsInt > 42000){
                return false;
            }
            return !name.isEmpty() && !name.isBlank();
        } catch (NumberFormatException e){
            return false;
        }
    }

    private void registerWorker() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("localhost", serverPort);
        Object server = registry.lookup("ControlCenter");
        if (server instanceof IRegistration){
            var registration = (IRegistration) server;
            registration.register(this);
            isConnected = true;
        }
    }
}
