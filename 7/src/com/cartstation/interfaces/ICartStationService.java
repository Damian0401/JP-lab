package com.cartstation.interfaces;

public interface ICartStationService {
    void addListener(ICartStationListener cartStationListener);
    boolean handleConnection(String name, String portAsString, String maxPlacesAsString);
    boolean handleDisconnect();
    void takePlace();
    void freePlaces();
}
