package com.park.common.interfaces;

public interface IClientApplicationListener extends IListener{
    void handleConnection(int port, String serverName);
    void handleDisconnection();
    void clearAttractions();
    void popUpMessage(String message);
}
