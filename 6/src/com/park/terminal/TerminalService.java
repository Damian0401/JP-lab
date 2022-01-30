package com.park.terminal;

import com.park.common.communication.MessageType;
import com.park.common.models.Attraction;
import com.park.terminal.abstractions.ATerminalService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TerminalService extends ATerminalService {
    @Override
    protected void handleClient(Socket socket) {
        try (socket) {
            var in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            var input = in.readLine().split(MessageType.Separator);
            switch (input[0]) {
                case MessageType.ServerDown:
                    handleServerDown();
                    break;
                case MessageType.DisplayAttraction:
                    handleDisplayAttraction(input[1], input[2], input[3], input[4]);
                    break;
                case MessageType.EditAttraction:
                    handleEditAttraction(input[1], input[2], input[3], input[4]);
                    break;
                default:
                    clientApplicationListeners.forEach(x -> x.displayMessage("Received unknown message"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connect(String host, int port) {
        connect(host, port, MessageType.RegisterTerminal);
    }

    @Override
    public void addAttraction(Attraction attraction) {
        try {
            var response = sender.send(serverHost, serverPort, MessageType.AddAttraction +
                    MessageType.Separator + attraction.getName() +
                    MessageType.Separator + attraction.getPlaceLimit(), true);
            if (response.equals(MessageType.Success)){
                clientApplicationListeners.forEach(x -> x.popUpMessage("Attraction added successfully"));
                return;
            }
            clientApplicationListeners.forEach(x -> x.displayMessage("Unable to add attraction"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
