package com.exercise.smart4viation.flightservice;

import com.exercise.smart4viation.flightservice.data.browser.DataBrowser;
import com.exercise.smart4viation.flightservice.data.reader.DataReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Smart4viationFlightServiceApplication extends SpringBootServletInitializer implements CommandLineRunner {
    @Autowired
    DataBrowser browser;

    public static void main(String[] args) {
        SpringApplication.run(Smart4viationFlightServiceApplication.class, args);
    }

    @Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Smart4viationFlightServiceApplication.class);
	}

    @Override
	public void run(String... args) {
//        DataReader dataReader = new DataReader();
//        System.out.println(dataReader.getFlightEntitiesList());
//        System.out.println(dataReader.getCargoEntitiesList());
        browser.getCargoInfo(6545, "2020-01-01T01:22:15 -01:00");
    }
}
