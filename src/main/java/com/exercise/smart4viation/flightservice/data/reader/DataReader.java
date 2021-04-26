package com.exercise.smart4viation.flightservice.data.reader;

import com.exercise.smart4viation.flightservice.domain.CargoEntity;
import com.exercise.smart4viation.flightservice.domain.FlightEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataReader {
    private static final String FLIGHT_PATH = "src/main/resources/jsonData/flight_entity.json";
    private static final String CARGO_PATH = "src/main/resources/jsonData/cargo_entity.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<FlightEntity> getFlightEntitiesList() {
        List<FlightEntity> flightList = new ArrayList<>();
        try {
            flightList = objectMapper.readValue(new FileReader(FLIGHT_PATH), new TypeReference<List<FlightEntity>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flightList;
    }

    public List<CargoEntity> getCargoEntitiesList() {
        List<CargoEntity> cargoList = new ArrayList<>();
        try {
            cargoList = objectMapper.readValue(new FileReader(CARGO_PATH), new TypeReference<List<CargoEntity>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cargoList;
    }
}
