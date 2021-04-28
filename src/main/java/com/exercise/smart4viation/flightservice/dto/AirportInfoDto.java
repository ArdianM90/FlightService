package com.exercise.smart4viation.flightservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AirportInfoDto {
    private String airportCode;
    private String date;
    private int departingFlightsQty;
    private int arrivalFlightsQty;
    private int departingBaggSum;
    private int arrivalBaggSum;

    @Override
    public String toString() {
        return "Airport "+airportCode+", "+date+":/n"+
        "Number of flights departing: "+departingFlightsQty+"/n"+
        "Number of flights arriving: "+arrivalFlightsQty+"/n"+
        "Total number (pieces) of baggage departing: "+departingBaggSum+"/n"+
        "Total number (pieces) of baggage arriving: "+arrivalBaggSum;
    }
}
