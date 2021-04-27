package com.exercise.smart4viation.flightservice.ui;

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
        return isInt(flightNo);
    }

    public boolean validateAirportCode(String code) {
        if (code.length() != 3) {
            return false;
        }
        if (!code.chars().allMatch(Character::isLetter)) {
            return false;
        }
        return code.chars().noneMatch(Character::isLowerCase);
    }

    public boolean validateDate(String date) {
        if (date.length() != 25 && date.length() != 26) {
            return false;
        }
        if (date.charAt(4) != '-' ||
                date.charAt(7) != '-' ||
                date.charAt(10) != 'T' ||
                date.charAt(13) != ':' ||
                date.charAt(16) != ':' ||
                date.charAt(19) != ' ' ||
                date.charAt(date.length()-3) != ':') {
            return false;
        }
        return isInt(date.substring(0, 4)) &&
                isInt(date.substring(5, 7)) &&
                isInt(date.substring(8, 10)) &&
                isInt(date.substring(11, 13)) &&
                isInt(date.substring(14, 16)) &&
                isInt(date.substring(17, 19)) &&
                isInt(date.substring(date.length() - 5, date.length() - 3)) &&
                isInt(date.substring(date.length() - 2));
    }

    private boolean isInt(String val) {
        try {
            Integer.parseInt(val);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
