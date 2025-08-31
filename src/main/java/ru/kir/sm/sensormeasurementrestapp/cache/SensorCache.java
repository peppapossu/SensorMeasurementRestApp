package ru.kir.sm.sensormeasurementrestapp.cache;

import ru.kir.sm.sensormeasurementrestapp.model.Sensor;

public interface SensorCache {

    Sensor getSensorByName(String name);

    void deleteSensorByName(String name);

    Sensor addSensor(String name, Sensor sensor);
}

