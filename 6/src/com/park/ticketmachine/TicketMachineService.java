package com.park.ticketmachine;

import com.park.common.communication.MessageType;
import com.park.common.models.Ticket;
import com.park.ticketmachine.abstractions.ATicketMachineService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TicketMachineService extends ATicketMachineService {
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
        connect(host, port, MessageType.RegisterTicketMachine);
    }

    @Override
    public void buyTicket(Ticket ticket) {
        try {
            var response = sender.send(serverHost, serverPort, MessageType.BuyTicket +
                    MessageType.Separator + ticket.getFirstName() +
                    MessageType.Separator + ticket.getLastName() +
                    MessageType.Separator + ticket.getAttractionId(), true);
            var splitResponse  = response.split(MessageType.Separator);
            if (splitResponse[0].equals(MessageType.Success)){
                clientApplicationListeners.forEach(x -> x.popUpMessage("Ticket bought successfully. Id: " + splitResponse[1]));
                return;
            }
            clientApplicationListeners.forEach(x -> x.displayMessage("Unable to buy ticket"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
