package com.exercise.smart4viation.flightservice;

import com.exercise.smart4viation.flightservice.data.JsonReader;
import com.exercise.smart4viation.flightservice.data.WeightCalc;
import com.exercise.smart4viation.flightservice.domain.CargoEntity;
import com.exercise.smart4viation.flightservice.domain.CargoUnit;
import com.exercise.smart4viation.flightservice.domain.FlightEntity;
import com.exercise.smart4viation.flightservice.dto.AirportInfoDto;
import com.exercise.smart4viation.flightservice.dto.CargoInfoDto;
import com.exercise.smart4viation.flightservice.ui.ChoiceValidator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {JsonReader.class,
        WeightCalc.class,
        CargoEntity.class,
        CargoUnit.class,
        FlightEntity.class,
        AirportInfoDto.class,
        CargoInfoDto.class,
        ChoiceValidator.class,
        Functionalities.class})
class Smart4viationFlightServiceApplicationTests {
    @Test
    void contextLoads() {
    }
}
