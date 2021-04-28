package com.exercise.smart4viation.flightservice.domain;

import lombok.Getter;

@Getter
public class FlightEntity {
    private int flightId;
    private int flightNumber;
    private String departureAirportIATACode;
    private String arrivalAirportIATACode;
    private String departureDate;
}
