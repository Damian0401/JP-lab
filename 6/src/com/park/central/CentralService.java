package com.park.central;

import com.park.central.interfaces.ICentralListener;
import com.park.central.interfaces.ICentralService;
import com.park.common.communication.MessageType;
import com.park.common.interfaces.IDataContext;
import com.park.common.interfaces.IReceiver;
import com.park.common.interfaces.ISender;
import com.park.common.models.Attraction;
import com.park.common.models.ClientApplication;
import com.park.common.communication.sockets.Receiver;
import com.park.common.communication.sockets.Sender;
import com.park.common.models.Ticket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class CentralService implements ICentralService{
    private final List<ICentralListener> centralListeners;
    private final IDataContext dataContext;
    private final ISender sender;
    private final List<Integer> terminalPorts;
    private final List<Integer> ticketMachinePorts;
    private final String clientHost = "localhost";
    private int lastTakenPort;
    private Thread receiverThread;
    private IReceiver receiver;


    public CentralService(IDataContext dataContext) {
        this.centralListeners = new LinkedList<>();
        this.dataContext = dataContext;
        this.terminalPorts = new LinkedList<>();
        this.ticketMachinePorts = new LinkedList<>();
        this.sender = new Sender();
    }

    private void handleClient(Socket socket){
        try (socket) {
            var in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            var out = new PrintWriter(socket.getOutputStream(), true);
            var input = in.readLine().split(MessageType.Separator);
            String response;
            switch (input[0]) {
                case MessageType.RegisterTerminal:
                    var terminalPort = registerTerminal();
                    out.println(terminalPort);
                    sendListOfAttractions(terminalPort);
                    break;
                case MessageType.RegisterTicketMachine:
                    var ticketMachinePort = registerTicketMachine();
                    out.println(ticketMachinePort);
                    sendListOfAttractions(ticketMachinePort);
                    break;
                case MessageType.AddAttraction:
                    response = addAttraction(input[1], input[2]);
                    out.println(response);
                    break;
                case MessageType.BuyTicket:
                    response = handleBuyTicket(input[1], input[2], input[3]);
                    out.println(response);
                    break;
                case MessageType.Unregister:
                    unregisterClientApplication(input[1]);
                    break;
                default:
                    centralListeners.forEach(x -> x.displayMessage("Received unknown message"));
            }
        } catch (IOException | IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    private String handleBuyTicket(String firstName, String lastName, String attractionIdAsString){
        var attractionIdAsInt = Integer.parseInt(attractionIdAsString);
        try {
            var attraction = dataContext.getAttractionById(attractionIdAsInt);
            if (attraction == null || attraction.getTicketsNumber() < 1)
                return MessageType.Fail;
            attraction.setTicketsNumber(attraction.getTicketsNumber() - 1);
            dataContext.updateAttractionById(attractionIdAsInt, attraction);
            var idOfNewTicket = dataContext.addTicket(new Ticket(firstName, lastName, attractionIdAsInt));
            centralListeners.forEach(x -> x.editAttraction(attractionIdAsInt, attraction));
            centralListeners.forEach(x -> x.displayMessage("Bought ticket for " + attraction.getName()));
            sendAttractionToEveryone(attraction, MessageType.EditAttraction);
            return MessageType.Success + MessageType.Separator + idOfNewTicket;
        } catch (IOException e) {
            e.printStackTrace();
            return MessageType.Fail;
        }
    }

    private void unregisterClientApplication(String clientApplicationPortAsString){
        var clientApplicationPortAsInt = Integer.parseInt(clientApplicationPortAsString);
        var isTerminalDeleted = terminalPorts.removeIf(x -> x == clientApplicationPortAsInt);
        if (isTerminalDeleted)
            centralListeners.forEach(x -> x.removeTerminal(new ClientApplication(clientApplicationPortAsInt, null)));
        var isTicketMachineDeleted = ticketMachinePorts.removeIf(x -> x == clientApplicationPortAsInt);
        if (isTicketMachineDeleted)
            centralListeners.forEach(x -> x.removeTicketMachine(new ClientApplication(clientApplicationPortAsInt, null)));
    }

    private void sendListOfAttractions(int port){
        try {
            var allAttractions = dataContext.getAttractions();
            for(var attraction : allAttractions){
                sender.send(clientHost, port, MessageType.DisplayAttraction +
                        MessageType.Separator + attraction.getId() +
                        MessageType.Separator + attraction.getName() +
                        MessageType.Separator + attraction.getTicketsNumber() +
                        MessageType.Separator + attraction.getPlaceLimit());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String addAttraction(String name, String placeNumberAsString){
        var placeNumberAsInt = Integer.parseInt(placeNumberAsString);
        var attraction = new Attraction(name, placeNumberAsInt);
        try {
            var allAttractions = dataContext.getAttractions();
            var isNameTaken = allAttractions.stream()
                    .anyMatch(x -> x.getName().equals(name));
            if (isNameTaken)
                return MessageType.Fail;
            dataContext.addAttraction(attraction);
            centralListeners.forEach(x -> x.addAttraction(attraction));
            centralListeners.forEach(x -> x.displayMessage("Added new attraction"));
            sendAttractionToEveryone(attraction, MessageType.DisplayAttraction);
            return MessageType.Success;
        } catch (IOException e) {
            e.printStackTrace();
            return MessageType.Fail;
        }
    }

    private void sendAttractionToEveryone(Attraction attraction, String messageType){
        for(var terminalPort : terminalPorts){
            try {
                sender.send(clientHost, terminalPort, messageType +
                        MessageType.Separator + attraction.getId() +
                        MessageType.Separator + attraction.getName() +
                        MessageType.Separator + attraction.getTicketsNumber() +
                        MessageType.Separator + attraction.getPlaceLimit());
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        for (var ticketMachinePort : ticketMachinePorts){
            try {
                sender.send(clientHost, ticketMachinePort, messageType +
                        MessageType.Separator + attraction.getId() +
                        MessageType.Separator + attraction.getName() +
                        MessageType.Separator + attraction.getTicketsNumber() +
                        MessageType.Separator + attraction.getPlaceLimit());
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private int registerTerminal(){
        lastTakenPort++;
        terminalPorts.add(lastTakenPort);
        centralListeners.forEach(x -> x.addTerminal(new ClientApplication(lastTakenPort, new Date())));
        centralListeners.forEach(x -> x.displayMessage("Added new terminal"));
        return lastTakenPort;
    }

    private int registerTicketMachine(){
        lastTakenPort++;
        ticketMachinePorts.add(lastTakenPort);
        centralListeners.forEach(x -> x.addTicketMachine(new ClientApplication(lastTakenPort, new Date())));
        centralListeners.forEach(x -> x.displayMessage("Added new ticket machine"));
        return lastTakenPort;
    }

    private void sendServerDownMessage(){
        for(var terminalPort : terminalPorts){
            try {
                sender.send(clientHost, terminalPort, MessageType.ServerDown);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        for (var ticketMachinePort : ticketMachinePorts){
            try {
                sender.send(clientHost, ticketMachinePort, MessageType.ServerDown);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void connect(int port) {
        if (receiver == null){
            try {
                receiver = new Receiver(port, this::handleClient);
                receiverThread = new Thread(receiver);
                receiverThread.start();
                lastTakenPort = port;
                centralListeners.forEach(x -> x.handleConnection(port));
                centralListeners.forEach(x -> x.displayMessage("Launched successfully"));
                return;
            } catch (IOException e) {
                receiver = null;
                e.printStackTrace();
            }
        }
        centralListeners.forEach(x -> x.displayMessage("Unable to launch"));
    }

    @Override
    public void disconnect() {
        if (receiver != null){
            try {
                receiver.finish();
                receiverThread.interrupt();
                centralListeners.forEach(ICentralListener::handleDisconnection);
                sendServerDownMessage();
                centralListeners.forEach(x -> x.displayMessage("Stopped successfully"));
                terminalPorts.clear();
                ticketMachinePorts.clear();
                receiver = null;
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
            centralListeners.forEach(x -> x.displayMessage("Unable to stop"));
        }
    }

    @Override
    public void addListener(ICentralListener centralListener) {
        centralListeners.add(centralListener);
    }

    @Override
    public List<Attraction> getAllAttractions() {
        List<Attraction> allAttractions;
        try {
            allAttractions = dataContext.getAttractions();
        }
        catch (IOException e){
            centralListeners.forEach(x -> x.displayMessage("Unable to load attractions"));
            allAttractions = new LinkedList<>();
        }
        return allAttractions;
    }
}
