package ru.kir.sm.sensormeasurementrestapp.service.impl;

import ru.kir.sm.sensormeasurementrestapp.cache.impl.SensorCacheImpl;
import ru.kir.sm.sensormeasurementrestapp.dto.SensorDto;
import ru.kir.sm.sensormeasurementrestapp.mapper.SensorMapper;
import ru.kir.sm.sensormeasurementrestapp.model.Sensor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kir.sm.sensormeasurementrestapp.service.SensorService;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SensorServiceImpl implements SensorService {

    private final SensorMapper sensorMapper;
    private final SensorCacheImpl sensorCache;

    @Transactional
    public void addSensor(SensorDto sensorDto) {
        var sensor = sensorMapper.toEntity(sensorDto);
        sensorCache.addSensor(sensor.getName(), sensor);
        log.info("Sensor name:'{}' added successfully", sensor.getName());
    }

    public Sensor getSensorByName(String name) {
        return sensorCache.getSensorByName(name);
    }

    public boolean isAlreadyExist(SensorDto sensorDto) {
        return getSensorByName(sensorDto.name()) != null;
    }


}
