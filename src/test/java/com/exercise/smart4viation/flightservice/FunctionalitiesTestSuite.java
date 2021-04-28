package com.exercise.smart4viation.flightservice;

import com.exercise.smart4viation.flightservice.data.JsonReader;
import com.exercise.smart4viation.flightservice.data.WeightCalc;
import com.exercise.smart4viation.flightservice.dto.AirportInfoDto;
import com.exercise.smart4viation.flightservice.dto.CargoInfoDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {Functionalities.class, JsonReader.class, WeightCalc.class})
class FunctionalitiesTestSuite {
    @Autowired
    Functionalities functionalities;

    @Test
    public void shouldReturnAirportInfoWhenRequestIsProper() {
        //Given
        String airportCode = "KRK";
        String date = "2020-01-01T01:22:15 -01:00";

        //When
        AirportInfoDto result = functionalities.getAirportInfo(airportCode, date);

        //Then
        assertEquals(airportCode, result.getAirportCode());
        assertEquals(date, result.getDate());
        assertEquals(0, result.getDepartingFlightsQty());
        assertEquals(1, result.getArrivalFlightsQty());
        assertEquals(0, result.getDepartingBaggSum());
        assertEquals(1724, result.getArrivalBaggSum());
    }

    @Test
    public void shouldReturnEmptyAirportInfo() {
        //Given
        String airportCode = "KRK";
        String date = "2014-01-01T01:22:15 -01:00";

        //When
        AirportInfoDto result = functionalities.getAirportInfo(airportCode, date);

        //Then
        assertEquals(airportCode, result.getAirportCode());
        assertEquals(date, result.getDate());
        assertEquals(0, result.getDepartingFlightsQty());
        assertEquals(0, result.getArrivalFlightsQty());
        assertEquals(0, result.getDepartingBaggSum());
        assertEquals(0, result.getArrivalBaggSum());
    }

    @Test
    public void shouldReturnCargoInfoWhenRequestIsProper() {
        //Given
        int flightNo = 6545;
        String date = "2020-01-01T01:22:15 -01:00";

        //When
        CargoInfoDto result = functionalities.getCargoInfo(flightNo, date);

        //Then
        assertEquals(flightNo, result.getFlightNumber());
        assertEquals(date, result.getDate());
        assertEquals(693, result.getBaggageWeight());
        assertEquals(916, result.getCargoWeight());
    }

    @Test
    public void shouldReturnEmptyCargoInfo() {
        //Given
        int flightNo = 1412;
        String date = "2014-01-01T01:22:15 -01:00";

        //When
        CargoInfoDto result = functionalities.getCargoInfo(flightNo, date);

        //Then
        assertEquals(flightNo, result.getFlightNumber());
        assertEquals(date, result.getDate());
        assertEquals(0, result.getCargoWeight());
        assertEquals(0, result.getBaggageWeight());
    }
}