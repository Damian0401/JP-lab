package com.park.central.interfaces;

import com.park.common.models.Attraction;

import java.util.List;

public interface ICentralService {
    void connect(int port);
    void disconnect();
    void addListener(ICentralListener centralListener);
    List<Attraction> getAllAttractions();
}
