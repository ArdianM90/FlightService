package com.exercise.smart4viation.flightservice;

import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ChoiceValidator {
    private static final String[] MAIN_MENU_CHOICES = {"f", "F", "a", "A", "x", "X"};

    public boolean validateMainMenuChoice(String choice) {
        return Arrays.asList(MAIN_MENU_CHOICES).contains(choice);
    }

    public boolean validateFlightNumber(String flightNo) {
        return false;
    }

    public boolean validateDate(String date) {
        return false;
    }

    public boolean validateAirportCode(String code) {
        return false;
    }
}
