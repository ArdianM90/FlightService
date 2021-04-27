package com.exercise.smart4viation.flightservice;

import com.exercise.smart4viation.flightservice.data.browser.DataBrowser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Scanner;

@SpringBootApplication
public class Smart4viationFlightServiceApplication implements CommandLineRunner {
    @Autowired
    DataBrowser browser;

    private static final String UI_INSTRUCTION = "Type \"F\" for flight information request, \"A\" for airport information request or \"X\" to quit application.";
    private final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        SpringApplication.run(Smart4viationFlightServiceApplication.class, args);
    }

    @Override
	public void run(String... args) {
//        browser.getCargoInfo(6545, "2020-01-01T01:22:15 -01:00");
//        browser.getAirportInfo("KRK", "2020-01-01T01:22:15 -01:00");
        boolean dontWantToQuit = true;
        while (dontWantToQuit) {
            System.out.println(UI_INSTRUCTION);
            String userChoice = input.nextLine();
            while (!validateChoice(userChoice)) {
                System.out.print("Wrong value. ");
                System.out.println(UI_INSTRUCTION);
                userChoice = input.nextLine();
            }
            switch (userChoice.toUpperCase()) {
                case "F":
                    System.out.println("Doing flight information request.");
                    break;
                case "A":
                    System.out.println("Doing airport information request.");
                    break;
                case "X":
                    dontWantToQuit = false;
                    break;
            }
        }
        System.exit(0);
    }

    private boolean validateChoice(String choice) {
        String[] validChoices = {"f", "F", "a", "A", "x", "X"};
        return Arrays.asList(validChoices).contains(choice);
    }
}
