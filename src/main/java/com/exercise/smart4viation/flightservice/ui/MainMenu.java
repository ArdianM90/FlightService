package com.exercise.smart4viation.flightservice.ui;

import com.exercise.smart4viation.flightservice.data.browser.DataBrowser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MainMenu {
    @Autowired
    DataBrowser browser;

    @Autowired
    ChoiceValidator validator;

    private static final String UI_INSTRUCTION = "Type \"F\" for flight information request, \"A\" for airport information request or \"X\" to quit application.";
    private static final String FLIGHT_NUMBER_REQ = "Type requested flight number (4 digits), for example \"6545\":";
    private static final String AIRPORT_CODE_REQ = "Type requested airport code (3 uppercase letter), for example \"KRK\":";
    private static final String DATE_REQ = "Type date in format YYYY-MM-DDTHH:MM:SS Z, for example \"2020-01-01T01:22:15 -01:00\":";
    private final Scanner input = new Scanner(System.in);
//    browser.getCargoInfo(6545, "2020-01-01T01:22:15 -01:00");
//    browser.getAirportInfo("KRK", "2020-01-01T01:22:15 -01:00");

    public void run() {
        boolean dontWantToQuit = true;
        while (dontWantToQuit) {
            System.out.println(UI_INSTRUCTION);
            String userChoice = input.nextLine();
            while (!validator.validateMainMenuChoice(userChoice)) {
                System.out.println("Wrong value. "+UI_INSTRUCTION);
                userChoice = input.nextLine();
            }
            switch (userChoice.toUpperCase()) {
                case "F":
                    runFlightInfo();
                    break;
                case "A":
                    runAirportInfo();
                    break;
                case "X":
                    dontWantToQuit = false;
                    break;
            }
        }
        System.exit(0);
    }

    private void runFlightInfo() {
        System.out.println(FLIGHT_NUMBER_REQ);
        String entry = input.nextLine();
        while (!validator.validateFlightNumber(entry)) {
            System.out.println("Wrong flight number format. "+FLIGHT_NUMBER_REQ);
            entry = input.nextLine();
        }
        int flightNo = Integer.parseInt(entry);
        System.out.println(DATE_REQ);
        entry = input.nextLine();
        while (!validator.validateDate(entry)) {
            System.out.println("Wrong date format. "+DATE_REQ);
            entry = input.nextLine();
        }
        String date = entry;
        //FlightInfo(flightNo, date)
    }

    private void runAirportInfo() {
        System.out.println(AIRPORT_CODE_REQ);
        String entry = input.nextLine();
        while (!validator.validateAirportCode(entry)) {
            System.out.println("Wrong airport code format. "+AIRPORT_CODE_REQ);
            entry = input.nextLine();
        }
        String airportCode = entry;
        System.out.println(DATE_REQ);
        entry = input.nextLine();
        while (!validator.validateDate(entry)) {
            System.out.println("Wrong date format. "+DATE_REQ);
            entry = input.nextLine();
        }
        String date = entry;
        //AirportInfo(airportCode, date)
    }
}
