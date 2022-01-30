package com.cartstation;

public class Main {
    public static void main(String[] args) {
        run();
    }

    private static void run(){
        var cartStation = new CartStation();
        var gui = new CartStationGUI(cartStation);
        cartStation.addListener(gui);
        gui.setVisible(true);
    }
}
