package com.worker;

import com.controlcenter.common.MessageType;
import shoppingCarts.interfaces.IInfo;
import shoppingCarts.interfaces.IRegistration;
import shoppingCarts.interfaces.IUpdate;

import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
    public static void main(String[] args) {
        run();
    }

    private static void run(){
        var workerService = new Worker();
        var gui = new WorkerGUI("Worker", workerService);
        workerService.addListener(gui);
        gui.setVisible(true);
    }
}
