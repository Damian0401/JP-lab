package com.controlcenter;

import java.rmi.RemoteException;

public class Main {
    public static void main(String[] args) {
        run();
    }

    private static void run(){
        ControlCenter controlCenter = null;
        try {
            controlCenter = new ControlCenter();
        } catch (RemoteException e) {
            e.printStackTrace();
            return;
        }
        var gui = new ControlCenterGUI("ControlCenter", controlCenter);
        controlCenter.addListener(gui);
        gui.setVisible(true);
    }
}
