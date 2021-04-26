package com.exercise.smart4viation.flightservice.domain;

import java.util.List;

public class CargoEntity {
    private int flightId;
    private List<CargoUnit> cargo;
    private List<CargoUnit> baggage;

    public int getFlightId() {
        return flightId;
    }

    public List<CargoUnit> getCargo() {
        return cargo;
    }

    public List<CargoUnit> getBaggage() {
        return baggage;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public void setCargo(List<CargoUnit> cargo) {
        this.cargo = cargo;
    }

    public void setBaggage(List<CargoUnit> baggage) {
        this.baggage = baggage;
    }
}
