package ru.kir.sm.sensormeasurementrestapp.service;

import ru.kir.sm.sensormeasurementrestapp.dto.MeasurementDto;
import ru.kir.sm.sensormeasurementrestapp.dto.MeasurementsResponse;

public interface MeasurementService {

    void add(MeasurementDto measurementDto);

    void addKafka(MeasurementDto measurementDto);

    MeasurementsResponse get();

    int rainyDaysCount();
}
