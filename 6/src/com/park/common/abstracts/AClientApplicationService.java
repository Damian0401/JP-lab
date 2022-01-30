package com.park.common.abstracts;

import com.park.common.communication.MessageType;
import com.park.common.communication.sockets.Receiver;
import com.park.common.communication.sockets.Sender;
import com.park.common.interfaces.IClientApplicationListener;
import com.park.common.interfaces.IReceiver;
import com.park.common.interfaces.ISender;
import com.park.common.models.Attraction;

import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public abstract class AClientApplicationService {
    private int clientPort;
    private Thread receiverThread;
    private IReceiver receiver;
    private boolean isServerAlive;
    protected final List<IClientApplicationListener> clientApplicationListeners;
    protected int serverPort;
    protected final ISender sender;
    protected String serverHost;

    public AClientApplicationService() {
        clientApplicationListeners = new LinkedList<>();
        sender = new Sender();
        isServerAlive = false;
    }

    public void addListener(IClientApplicationListener ticketMachineListener){
        clientApplicationListeners.add(ticketMachineListener);
    }

    protected abstract void handleClient(Socket socket);

    protected void handleDisplayAttraction(String idAsString, String name, String ticketsNumberAsString, String placeLimitAsString){
        var idAsInt = Integer.parseInt(idAsString);
        var ticketsNumberAsInt = Integer.parseInt(ticketsNumberAsString);
        var placeLimitAsInt = Integer.parseInt(placeLimitAsString);
        var attractionToAdd = new Attraction(idAsInt, name, ticketsNumberAsInt, placeLimitAsInt);
        clientApplicationListeners.forEach(x -> x.addAttraction(attractionToAdd));
        clientApplicationListeners.forEach(x -> x.displayMessage("Added new attraction"));
    }

    protected void handleServerDown(){
        clientApplicationListeners.forEach(IClientApplicationListener::handleDisconnection);
        clientApplicationListeners.forEach(x -> x.displayMessage("Server is down"));
        isServerAlive = false;
        disconnect();
    }

    protected void handleEditAttraction(String idAsString, String name, String ticketsNumberAsString, String placeLimitAsString){
        var idAsInt = Integer.parseInt(idAsString);
        var ticketsNumberAsInt = Integer.parseInt(ticketsNumberAsString);
        var placeLimitAsInt = Integer.parseInt(placeLimitAsString);
        var attractionToEdit = new Attraction(idAsInt, name, ticketsNumberAsInt, placeLimitAsInt);
        clientApplicationListeners.forEach(x -> x.editAttraction(idAsInt, attractionToEdit));
        clientApplicationListeners.forEach(x -> x.displayMessage("Edited attraction with id: " + idAsString));
    }

    abstract public void connect(String host, int port);

    protected void connect(String host, int port, String messageType) {
        if (receiver == null){
            try {
                var serverName = host + "/" + port;
                var portAsString = sender.send(host, port, messageType, true);
                var portAsInt = Integer.parseInt(portAsString);
                clientApplicationListeners.forEach(x -> x.displayMessage("Connected to " + serverName));
                clientApplicationListeners.forEach(x -> x.handleConnection(portAsInt, serverName));
                clientPort = portAsInt;
                serverPort = port;
                serverHost = host;
                receiver = new Receiver(portAsInt, this::handleClient);
                receiverThread = new Thread(receiver);
                receiverThread.start();
                isServerAlive = true;
                return;
            } catch (IOException e) {
                receiver = null;
            }
        }
        clientApplicationListeners.forEach(x -> x.displayMessage("Unable to connect"));
    }

    public void disconnect() {
        try {
            if (isServerAlive)
                sender.send(serverHost, serverPort, MessageType.Unregister + MessageType.Separator + clientPort);
            clientApplicationListeners.forEach(x -> x.displayMessage("Disconnected"));
            clientApplicationListeners.forEach(IClientApplicationListener::handleDisconnection);
            clientApplicationListeners.forEach(IClientApplicationListener::clearAttractions);
            receiver.finish();
            receiverThread.interrupt();
            receiver = null;
        } catch (IOException e) {
            e.printStackTrace();
            clientApplicationListeners.forEach(x -> x.displayMessage("Unable to disconnect"));
        }
    }
}
