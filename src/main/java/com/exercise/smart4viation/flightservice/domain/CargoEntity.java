package com.exercise.smart4viation.flightservice.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class CargoEntity {
    private int flightId;
    private List<CargoUnit> cargo;
    private List<CargoUnit> baggage;
}
