package com.exercise.smart4viation.flightservice.ui;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = ChoiceValidator.class)
class DateValidationTestSuite {
    @Autowired
    ChoiceValidator validator;

    @Test
    public void shouldPassDateWithNegativeZulu() {
        //Given
        String date = "2020-01-01T01:22:15 -01:00";

        //When&Then
        assertTrue(validator.validateDate(date));
    }

    @Test
    public void shouldPassDateWithPositiveZulu() {
        //Given
        String date = "2020-01-01T01:22:15 01:00";

        //When&Then
        assertTrue(validator.validateDate(date));
    }

    @Test
    public void shouldNotPassEmptyString() {
        //Given
        String date = "";

        //When&Then
        assertFalse(validator.validateDate(date));
    }

    @Test
    public void shouldNotPassDateWithInvalidCharacter() {
        //Given
        String date = "2020X01-01T01:22:15 01:00";

        //When&Then
        assertFalse(validator.validateDate(date));
    }
}