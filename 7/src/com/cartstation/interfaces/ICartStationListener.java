package com.cartstation.interfaces;

public interface ICartStationListener {
    void displayMessage(String message);
    void updateCartStation(int occupancy, int capacity);
}
