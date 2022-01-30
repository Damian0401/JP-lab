package com.park.ticketmachine;

public class TicketMachineLauncher {
    public static void main(String[] args) {
        run();
    }

    private static void run(){
        var ticketMachineService = new TicketMachineService();
        var ticketMachineGUI = new TicketMachineGUI(ticketMachineService);
        ticketMachineService.addListener(ticketMachineGUI);
        ticketMachineGUI.run();
    }
}
