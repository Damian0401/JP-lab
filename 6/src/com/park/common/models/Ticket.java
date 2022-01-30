package com.park.common.models;

public class Ticket {
    private int id;
    private String firstName;
    private String lastName;
    private int attractionId;

    public Ticket() {
    }

    public Ticket(String firstName, String lastName, int attractionId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.attractionId = attractionId;
    }

    public Ticket(int id, String firstName, String lastName, int attractionId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.attractionId = attractionId;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAttractionId() {
        return attractionId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAttractionId(int attractionId) {
        this.attractionId = attractionId;
    }
}
