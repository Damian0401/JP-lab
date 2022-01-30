package com.park.common.communication.sockets;

import com.park.common.interfaces.IHandleClientSocket;
import com.park.common.interfaces.IReceiver;

import java.io.IOException;
import java.net.ServerSocket;

public class Receiver implements IReceiver {
    private final ServerSocket serverSocket;
    private final IHandleClientSocket doSomething;
    private boolean isFinished = false;

    public Receiver(int port, IHandleClientSocket doSomething) throws IOException {
        this.doSomething = doSomething;
        this.serverSocket = new ServerSocket(port);
    }

    @Override
    public void run() {
        while (!isFinished){
            try {
                var client = serverSocket.accept();
                doSomething.performAction(client);
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void finish() throws IOException {
        serverSocket.close();
        isFinished = true;
    }
}
