package ru.kir.sm.sensormeasurementrestapp.services;

import jakarta.persistence.EntityExistsException;
import ru.kir.sm.sensormeasurementrestapp.caches.SensorCache;
import ru.kir.sm.sensormeasurementrestapp.dto.SensorDto;
import ru.kir.sm.sensormeasurementrestapp.mapper.SensorMapper;
import ru.kir.sm.sensormeasurementrestapp.models.Sensor;
import ru.kir.sm.sensormeasurementrestapp.repositories.SensorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SensorService {

    private final SensorMapper sensorMapper;
    private final SensorCache sensorCache;

    @Transactional
    public void addSensor(SensorDto sensorDto) {

        if (isAlreadyExist(sensorDto)) {
            log.warn("Sensor {} already exist", sensorDto.name());
            throw new EntityExistsException("Sensor with name " + sensorDto.name() + " already exists");
        }
        var sensor = sensorMapper.toEntity(sensorDto);
        sensorCache.addSensor(sensor.getName(), sensor);
        log.info("Sensor added successfully");
    }

    public Sensor getSensorByName(String name) {
        return sensorCache.getSensorByName(name);
    }

    public boolean isAlreadyExist(SensorDto sensorDto) {
        return getSensorByName(sensorDto.name()) != null;
    }


}
