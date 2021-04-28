package com.exercise.smart4viation.flightservice;

import com.exercise.smart4viation.flightservice.data.JsonReader;
import com.exercise.smart4viation.flightservice.domain.CargoEntity;
import com.exercise.smart4viation.flightservice.domain.CargoUnit;
import com.exercise.smart4viation.flightservice.domain.FlightEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Functionalities {
    @Autowired
    JsonReader jsonReader;

    @Autowired
    WeightCalc calculator;

    public void getCargoInfo(int flightNumber, String date) {
        int flightId = jsonReader.getFlightEntitiesList().stream()
                .filter(e -> e.getFlightNumber() == flightNumber)
                .filter(e -> e.getDepartureDate().equals(date))
                .map(FlightEntity::getFlightId)
                .findFirst().get();
        int cargoWeight = computeCargoWeightById(flightId);
        int baggageWeight = computeBaggageWeightById(flightId);
        System.out.println("Cargo weight (kg): "+cargoWeight);
        System.out.println("Baggage weight (kg): "+baggageWeight);
        System.out.println("Total weight (kg): "+(cargoWeight+baggageWeight));
    }

    public void getAirportInfo(String airportCode, String date) {
        List<Integer> arrivalFlights = getArrivalFlightsToAirport(airportCode, date);
        List<Integer> departingFlights = getDepartingFlightsFromAirport(airportCode, date);
        System.out.println("Number of flights departing from "+airportCode+": "+departingFlights.size());
        System.out.println("Number of flights arriving to "+airportCode+": "+arrivalFlights.size());
        int arrBaggSum = arrivalFlights.stream()
                .map(this::computeBaggageSumById)
                .mapToInt(Integer::intValue)
                .sum();
        int depBaggSum = departingFlights.stream()
                .map(this::computeBaggageSumById)
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("Total number (pieces) of baggage arriving to "+airportCode+": "+arrBaggSum);
        System.out.println("Total number (pieces) of baggage departing from "+airportCode+": "+depBaggSum);
    }

    private int computeCargoWeightById(int flightId) {
        int lbSum = jsonReader.getCargoListByFlightId(flightId).stream()
                .filter(e -> e.getWeightUnit().equals("lb"))
                .map(CargoUnit::getWeight)
                .mapToInt(Integer::intValue).sum();
        int kgSum = jsonReader.getCargoListByFlightId(flightId).stream()
                .filter(e -> e.getWeightUnit().equals("kg"))
                .map(CargoUnit::getWeight)
                .mapToInt(Integer::intValue).sum();
        return kgSum + (calculator.computeLbsToKgs(lbSum));
    }

    private int computeBaggageWeightById(int flightId) {
        int lbSum = jsonReader.getBaggageListByFlightId(flightId).stream()
                .filter(e -> e.getWeightUnit().equals("lb"))
                .map(CargoUnit::getWeight)
                .mapToInt(Integer::intValue).sum();
        int kgSum = jsonReader.getBaggageListByFlightId(flightId).stream()
                .filter(e -> e.getWeightUnit().equals("kg"))
                .map(CargoUnit::getWeight)
                .mapToInt(Integer::intValue).sum();
        return kgSum + (calculator.computeLbsToKgs(lbSum));
    }

    private int computeBaggageSumById(int flightId) {
        return jsonReader.getCargoEntitiesList().stream()
                .filter(e -> e.getFlightId() == flightId)
                .map(CargoEntity::getBaggage)
                .collect(Collectors.toList())
                .stream()
                .flatMap(List::stream)
                .map(CargoUnit::getPieces)
                .mapToInt(Integer::intValue).sum();
    }

    private List<Integer> getArrivalFlightsToAirport(String airportCode, String date) {
        return jsonReader.getFlightEntitiesList().stream()
                .filter(e -> e.getArrivalAirportIATACode().equals(airportCode))
                .filter(e -> e.getDepartureDate().equals(date))
                .map(FlightEntity::getFlightId)
                .collect(Collectors.toList());
    }

    private List<Integer> getDepartingFlightsFromAirport(String airportCode, String date) {
        return jsonReader.getFlightEntitiesList().stream()
                .filter(e -> e.getDepartureAirportIATACode().equals(airportCode))
                .filter(e -> e.getDepartureDate().equals(date))
                .map(FlightEntity::getFlightId)
                .collect(Collectors.toList());
    }
}
