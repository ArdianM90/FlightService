package com.exercise.smart4viation.flightservice.data.browser;

import com.exercise.smart4viation.flightservice.WeightCalc;
import com.exercise.smart4viation.flightservice.data.reader.DataReader;
import com.exercise.smart4viation.flightservice.domain.CargoEntity;
import com.exercise.smart4viation.flightservice.domain.CargoUnit;
import com.exercise.smart4viation.flightservice.domain.FlightEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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
        List<Integer> arrivalFlights = getArrivalFlightsToAirport(airportCode);
        List<Integer> departingFlights = getDepartingFlightsFromAirport(airportCode);
        System.out.println("Number of flights departing from "+airportCode+": "+departingFlights.size());
        System.out.println("Number of flights arriving to "+airportCode+": "+arrivalFlights.size());
        int arrBaggSum = arrivalFlights.stream()
                .map(this::getBaggageSumBy)
                .mapToInt(Integer::intValue)
                .sum();
        int depBaggSum = departingFlights.stream()
                .map(this::getBaggageSumBy)
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("Total number (pieces) of baggage arriving to "+airportCode+": "+arrBaggSum);
        System.out.println("Total number (pieces) of baggage departing from "+airportCode+": "+depBaggSum);
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

    private List<Integer> getArrivalFlightsToAirport(String airportCode) {
        return dataReader.getFlightEntitiesList().stream()
                .filter(e -> e.getArrivalAirportIATACode().equals(airportCode))
                .map(FlightEntity::getFlightId)
                .collect(Collectors.toList());
    }

    private List<Integer> getDepartingFlightsFromAirport(String airportCode) {
        return dataReader.getFlightEntitiesList().stream()
                .filter(e -> e.getDepartureAirportIATACode().equals(airportCode))
                .map(FlightEntity::getFlightId)
                .collect(Collectors.toList());
    }

    private int getBaggageSumBy(int flightId) {
        return dataReader.getCargoEntitiesList().stream()
                .filter(e -> e.getFlightId() == flightId)
                .map(CargoEntity::getBaggage)
                .collect(Collectors.toList())
                .stream()
                .flatMap(List::stream)
                .map(CargoUnit::getPieces)
                .mapToInt(Integer::intValue).sum();
    }
}
