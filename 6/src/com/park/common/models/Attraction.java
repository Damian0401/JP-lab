package com.park.common.models;

import java.util.Locale;
import java.util.Objects;

public class Attraction {
    private int id;
    private String name;
    private int ticketsNumber;
    private int placeLimit;

    public Attraction(String name, int placeLimit) {
        this.name = name;
        this.placeLimit = placeLimit;
        this.ticketsNumber = placeLimit;
    }

    public Attraction(int id, String name, int ticketsNumber, int placeLimit) {
        this.id = id;
        this.name = name;
        this.ticketsNumber = ticketsNumber;
        this.placeLimit = placeLimit;
    }

    public Attraction(){

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTicketsNumber(int ticketsNumber) {
        this.ticketsNumber = ticketsNumber;
    }

    public void setPlaceLimit(int placeLimit) {
        this.placeLimit = placeLimit;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getTicketsNumber() {
        return ticketsNumber;
    }

    public int getPlaceLimit() {
        return placeLimit;
    }

    @Override
    public String toString() {
        return name.toUpperCase(Locale.ROOT);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Attraction)) return false;
        Attraction that = (Attraction) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
