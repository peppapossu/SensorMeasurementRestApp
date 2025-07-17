package ru.kir.sm.sensormeasurementrestapp.services;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kir.sm.sensormeasurementrestapp.dto.MeasurementDto;
import ru.kir.sm.sensormeasurementrestapp.mapper.MeasurementMapper;
import ru.kir.sm.sensormeasurementrestapp.models.Measurement;
import ru.kir.sm.sensormeasurementrestapp.models.Sensor;
import ru.kir.sm.sensormeasurementrestapp.redis.RedisService;
import ru.kir.sm.sensormeasurementrestapp.repositories.MeasurementRepository;

import java.util.List;

@Slf4j
@Service
//@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final MeasurementMapper mapper;
    private final SensorService sensorService;
    private final RedisService redisService;

    @Transactional
    public void add(MeasurementDto measurementDto) {
        String sensorName = measurementDto.getSensor().getName();

        Sensor sensor = sensorService.getSensorByName(sensorName);
//        SensorDto sensor = redisService.getSensor(sensorName);
               if (sensor == null) {
                    log.error("Sensor '{}' does not exist", sensorName);
                    throw new EntityNotFoundException("Sensor '" + sensorName + "' does not exist");
                }

        Measurement measurement = mapper.toEntity(measurementDto);
        measurement.setSensor(sensor);

        measurementRepository.save(measurement);
        log.info("Saved new measurement for sensor '{}'", sensorName);
    }

    public List<MeasurementDto> get() {
        return mapper.toDto(measurementRepository.findAll());
    }

    public int rainyDaysCount() {
        return measurementRepository.findAllByRainingIsTrue();
    }
}
