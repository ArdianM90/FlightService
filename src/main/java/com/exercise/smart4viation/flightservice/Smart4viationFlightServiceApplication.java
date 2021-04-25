package com.exercise.smart4viation.flightservice;

import com.exercise.smart4viation.flightservice.data.reader.DataReader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Smart4viationFlightServiceApplication extends SpringBootServletInitializer implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Smart4viationFlightServiceApplication.class, args);
    }

    @Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Smart4viationFlightServiceApplication.class);
	}

    @Override
	public void run(String... args) {
        DataReader dataReader = new DataReader();
        System.out.println(dataReader.getFlightEntity());
        System.out.println(dataReader.getCargoEntity());
    }
}
