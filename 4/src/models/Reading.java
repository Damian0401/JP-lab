package models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import utils.enums.ReadType;
import utils.jackson.LocalDateDeserializer;
import utils.jackson.LocalDateSerializer;

import java.time.LocalDate;

public class Reading {
    private int id;
    private double value;
    private String pickupPointId;
    private ReadType readType;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getPickupPointId() {
        return pickupPointId;
    }

    public void setPickupPointId(String pickupPointId) {
        this.pickupPointId = pickupPointId;
    }

    public ReadType getReadType() {
        return readType;
    }

    public void setReadType(ReadType readType) {
        this.readType = readType;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
