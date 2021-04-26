package com.exercise.smart4viation.flightservice.data.browser;

import com.exercise.smart4viation.flightservice.WeightCalc;
import com.exercise.smart4viation.flightservice.data.reader.DataReader;
import com.exercise.smart4viation.flightservice.domain.CargoUnit;
import com.exercise.smart4viation.flightservice.domain.FlightEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataBrowser {
    @Autowired
    DataReader dataReader;
    @Autowired
    WeightCalc weightCalculator;

    public void getCargoInfo(int flightNumber, String date) {
//        a. Cargo Weight for requested Flight
//        b. Baggage Weight for requested Flight
//        c. Total Weight for requested Flight
        int flightId = dataReader.getFlightEntitiesList().stream()
                .filter(e -> e.getFlightNumber() == flightNumber)
                .filter(e -> e.getDepartureDate().equals(date))
                .map(FlightEntity::getFlightId)
                .findFirst().get();
        int cargoWeight = getCargoWeightBy(flightId);
        int baggageWeight = getBaggageWeightBy(flightId);
        System.out.println("Cargo weight (kg): "+cargoWeight);
        System.out.println("Baggage weight (kg): "+baggageWeight);
        System.out.println("Total weight (kg): "+(cargoWeight+baggageWeight));
    }

    public void getAirportInfo(String airportCode, String date) {
//        a. Number of flights departing from this airport,
//        b. Number of flights arriving to this airport,
//        c. Total number (pieces) of baggage arriving to this airport,
//        d. Total number (pieces) of baggage departing from this airport.
    }

    private int getCargoWeightBy(int flightId) {
        int lbSum = dataReader.getCargoListByFlightId(flightId).stream()
                .filter(e -> e.getWeightUnit().equals("lb"))
                .map(CargoUnit::getWeight)
                .mapToInt(Integer::intValue).sum();
        int kgSum = dataReader.getCargoListByFlightId(flightId).stream()
                .filter(e -> e.getWeightUnit().equals("kg"))
                .map(CargoUnit::getWeight)
                .mapToInt(Integer::intValue).sum();
        return kgSum + (weightCalculator.computeLbsToKgs(lbSum));
    }

    private int getBaggageWeightBy(int flightId) {
        int lbSum = dataReader.getBaggageListByFlightId(flightId).stream()
                .filter(e -> e.getWeightUnit().equals("lb"))
                .map(CargoUnit::getWeight)
                .mapToInt(Integer::intValue).sum();
        int kgSum = dataReader.getBaggageListByFlightId(flightId).stream()
                .filter(e -> e.getWeightUnit().equals("kg"))
                .map(CargoUnit::getWeight)
                .mapToInt(Integer::intValue).sum();
        return kgSum + (weightCalculator.computeLbsToKgs(lbSum));
    }
}
