package models;

import enums.PlaceForGuestStatus;

import javax.swing.*;

public class PlaceForGuest {
    private final int numberOfPlace;
    private final JLabel placeLabel;
    private PlaceForGuestStatus status;

    public PlaceForGuest(JLabel placeLabel, int numberOfPlace) {
        this.numberOfPlace = numberOfPlace;
        this.placeLabel = placeLabel;
        initialize();
    }

    private void initialize(){
        status = PlaceForGuestStatus.Empty;
        placeLabel.setText("[ ]");
    }

    public synchronized boolean  takePlace(int preferredNumberOfDish){
        if (status == PlaceForGuestStatus.Empty){
            status = PlaceForGuestStatus.Taken;
            placeLabel.setText("[" + preferredNumberOfDish + "]");
            return true;
        }
        return false;
    }

    public synchronized PlaceForGuestStatus getStatus() {
        return status;
    }

    public int getNumberOfPlace() {
        return numberOfPlace;
    }

    public synchronized void freePlace(){
        status = PlaceForGuestStatus.Empty;
        placeLabel.setText("[ ]");
    }
}
