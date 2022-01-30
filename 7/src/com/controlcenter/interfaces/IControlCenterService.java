package com.controlcenter.interfaces;

public interface IControlCenterService {
    void addListener(IControlCenterListener controlCenterListener);
    void handleConnection(String portAsString);
}
