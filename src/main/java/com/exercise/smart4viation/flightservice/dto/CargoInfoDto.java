package com.exercise.smart4viation.flightservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CargoInfoDto {
    private int flightNumber;
    private String date;
    private int cargoWeight;
    private int baggageWeight;

    @Override
    public String toString() {
        return "Flight No."+flightNumber+", "+date+":/n"+
        "Cargo weight (kg): "+cargoWeight+"/n"+
        "Baggage weight (kg): "+baggageWeight+"/n"+
        "Total weight (kg): "+(cargoWeight+baggageWeight);
    }
}
