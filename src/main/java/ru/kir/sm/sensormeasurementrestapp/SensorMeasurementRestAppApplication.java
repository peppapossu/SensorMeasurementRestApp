package ru.kir.sm.sensormeasurementrestapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class SensorMeasurementRestAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SensorMeasurementRestAppApplication.class, args);
    }

}
