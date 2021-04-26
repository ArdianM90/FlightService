package com.exercise.smart4viation.flightservice.domain;

public class CargoUnit {
    private int id;
    private int weight;
    private String weightUnit;
    private int pieces;

    public int getId() {
        return id;
    }

    public int getWeight() {
        return weight;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public int getPieces() {
        return pieces;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }
}
