package com.exercise.smart4viation.flightservice;

import com.exercise.smart4viation.flightservice.data.browser.DataBrowser;
import com.exercise.smart4viation.flightservice.ui.ChoiceValidator;
import com.exercise.smart4viation.flightservice.ui.MainMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
//public class Smart4viationFlightServiceApplication implements CommandLineRunner {
public class Smart4viationFlightServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(Smart4viationFlightServiceApplication.class, args);
        MainMenu main = new MainMenu();
        main.run();
    }

//    @Override
//	public void run(String... args) {
//
//    }
}
