package com.exercise.smart4viation.flightservice;

import com.exercise.smart4viation.flightservice.data.JsonReader;
import com.exercise.smart4viation.flightservice.data.WeightCalc;
import com.exercise.smart4viation.flightservice.domain.CargoEntity;
import com.exercise.smart4viation.flightservice.domain.CargoUnit;
import com.exercise.smart4viation.flightservice.domain.FlightEntity;
import com.exercise.smart4viation.flightservice.dto.AirportInfoDto;
import com.exercise.smart4viation.flightservice.dto.CargoInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Functionalities {
    @Autowired
    private JsonReader jsonReader;

    @Autowired
    private WeightCalc calculator;

    private static final int EMPTY = -1;

    public CargoInfoDto getCargoInfo(final int flightNumber, final String date) {
        int flightId = jsonReader.getFlightEntitiesList().stream()
                .filter(e -> e.getFlightNumber() == flightNumber)
                .filter(e -> e.getDepartureDate().equals(date))
                .map(FlightEntity::getFlightId)
                .findFirst()
                .orElse(EMPTY);
        int cargoWeight = flightId != EMPTY ? computeCargoWeightById(flightId) : 0;
        int baggageWeight = flightId != EMPTY ? computeBaggageWeightById(flightId) : 0;
        return new CargoInfoDto(flightNumber, date, cargoWeight, baggageWeight);
    }

    public AirportInfoDto getAirportInfo(final String airportCode, final String date) {
        List<Integer> arrivalFlights = getArrivalFlightsToAirport(airportCode, date);
        List<Integer> departingFlights = getDepartingFlightsFromAirport(airportCode, date);
        int arrBaggSum = arrivalFlights.stream()
                .map(this::computeBaggageSumById)
                .mapToInt(Integer::intValue)
                .sum();
        int depBaggSum = departingFlights.stream()
                .map(this::computeBaggageSumById)
                .mapToInt(Integer::intValue)
                .sum();
        return new AirportInfoDto(airportCode, date, departingFlights.size(), arrivalFlights.size(), depBaggSum, arrBaggSum);
    }

    private int computeCargoWeightById(final int flightId) {
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

    private int computeBaggageWeightById(final int flightId) {
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

    private int computeBaggageSumById(final int flightId) {
        return jsonReader.getCargoEntitiesList().stream()
                .filter(e -> e.getFlightId() == flightId)
                .map(CargoEntity::getBaggage)
                .collect(Collectors.toList())
                .stream()
                .flatMap(List::stream)
                .map(CargoUnit::getPieces)
                .mapToInt(Integer::intValue).sum();
    }

    private List<Integer> getArrivalFlightsToAirport(final String airportCode, final String date) {
        return jsonReader.getFlightEntitiesList().stream()
                .filter(e -> e.getArrivalAirportIATACode().equals(airportCode))
                .filter(e -> e.getDepartureDate().equals(date))
                .map(FlightEntity::getFlightId)
                .collect(Collectors.toList());
    }

    private List<Integer> getDepartingFlightsFromAirport(final String airportCode, final String date) {
        return jsonReader.getFlightEntitiesList().stream()
                .filter(e -> e.getDepartureAirportIATACode().equals(airportCode))
                .filter(e -> e.getDepartureDate().equals(date))
                .map(FlightEntity::getFlightId)
                .collect(Collectors.toList());
    }
}
