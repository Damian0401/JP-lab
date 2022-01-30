package models;

import java.time.LocalDate;

public class Pair {
    private LocalDate key;

    public Pair(LocalDate key, double value) {
        this.key = key;
        this.value = value;
    }

    public LocalDate getKey() {
        return key;
    }

    public void setKey(LocalDate key) {
        this.key = key;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    private double value;
}
