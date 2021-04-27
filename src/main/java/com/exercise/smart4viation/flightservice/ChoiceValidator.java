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
        if (flightNo.length() != 4) {
            return false;
        }
        try {
            Integer.parseInt(flightNo);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public boolean validateDate(String date) {
        return false;
    }

    public boolean validateAirportCode(String code) {
        if (code.length() != 3) {
            return false;
        }
        if (!code.chars().allMatch(Character::isLetter)) {
            return false;
        };
        return code.chars().noneMatch(Character::isLowerCase);
    }
}
