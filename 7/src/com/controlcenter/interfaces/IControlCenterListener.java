package com.controlcenter.interfaces;

import shoppingCarts.data.UpdateInfo;

public interface IControlCenterListener {
    void displayMessage(String message);
    void changeStatus(String status);
    void displayWorker(String name);
    void removeWorker(String name);
    void displayCartStation(UpdateInfo updateInfo);
    void removeCartStation(String stationName);
    void updateCartStation(UpdateInfo updateInfo);
}
