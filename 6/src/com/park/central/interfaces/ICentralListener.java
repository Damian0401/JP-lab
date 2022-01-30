package com.park.central.interfaces;

import com.park.common.interfaces.IListener;
import com.park.common.models.ClientApplication;

public interface ICentralListener extends IListener {
    void handleConnection(int port);
    void handleDisconnection();
    void addTerminal(ClientApplication clientApplication);
    void addTicketMachine(ClientApplication clientApplication);
    void removeTerminal(ClientApplication clientApplication);
    void removeTicketMachine(ClientApplication clientApplication);
}
