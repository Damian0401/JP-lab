package com.park.common.communication.sockets;

import com.park.common.interfaces.ISender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Sender implements ISender {

    @Override
    public String send(String host, int port, String message) throws IOException {
        send(host, port, message, false);
        return null;
    }

    @Override
    public String send(String host, int port, String message, boolean isResponse) throws IOException {
        Socket socket = new Socket(host, port);
        var out = new PrintWriter(socket.getOutputStream(), true);
        out.println(message);
        String response = null;
        if (isResponse){
            var in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            response = in.readLine();
        }
        socket.close();
        return response;
    }
}
