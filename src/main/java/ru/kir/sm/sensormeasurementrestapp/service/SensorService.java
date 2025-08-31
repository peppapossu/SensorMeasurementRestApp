package ru.kir.sm.sensormeasurementrestapp.service;

import ru.kir.sm.sensormeasurementrestapp.dto.SensorDto;
import ru.kir.sm.sensormeasurementrestapp.model.Sensor;

public interface SensorService {

    void addSensor(SensorDto sensorDto);

    Sensor getSensorByName(String name);

    boolean isAlreadyExist(SensorDto sensorDto);
}
