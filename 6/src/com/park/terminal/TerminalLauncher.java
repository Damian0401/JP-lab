package com.park.terminal;

public class TerminalLauncher {
    public static void main(String[] args) {
        run();
    }

    public static void run(){
        var terminalService = new TerminalService();
        var terminalGUI = new TerminalGUI(terminalService);
        terminalService.addListener(terminalGUI);
        terminalGUI.run();
    }
}
