package com.exercise.smart4viation.flightservice.data.reader;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;

@Component
public class DataReader {
    private static final String FLIGHT_PATH = "src/main/resources/jsonData/flight_entity.json";
    private static final String CARGO_PATH = "src/main/resources/jsonData/cargo_entity.json";
    private static final JSONParser parser = new JSONParser();

    public Object getFlightEntity() {
        Object obj = new Object();
        try {
            obj = parser.parse(new FileReader(FLIGHT_PATH));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public Object getCargoEntity() {
        Object obj = new Object();
        try {
            obj = parser.parse(new FileReader(CARGO_PATH));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
