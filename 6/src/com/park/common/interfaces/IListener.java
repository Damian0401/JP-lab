package com.park.common.interfaces;

import com.park.common.models.Attraction;

public interface IListener {
    void displayMessage(String message);
    void addAttraction(Attraction attraction);
    void editAttraction(int attractionId, Attraction attraction);
}
