package ru.kir.sm.sensormeasurementrestapp.services;

import jakarta.persistence.EntityExistsException;
import ru.kir.sm.sensormeasurementrestapp.caches.SensorCache;
import ru.kir.sm.sensormeasurementrestapp.dto.SensorDto;
import ru.kir.sm.sensormeasurementrestapp.mapper.SensorMapper;
import ru.kir.sm.sensormeasurementrestapp.models.Sensor;
import ru.kir.sm.sensormeasurementrestapp.repositories.SensorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
//@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SensorService {

    private final SensorRepository sensorRepository;
    private final SensorMapper sensorMapper;
    private final SensorCache sensorCache;

    @Transactional
    public void addSensor(SensorDto sensorDto) {
        isAlreadyExist(sensorDto);

        sensorRepository.save(sensorMapper.toEntity(sensorDto));
        log.info("Sensor added successfully");
    }

    public Sensor getSensorByName(String name){
        return sensorCache.getSensorByName(name);
    }

//    @CacheEvict(value = "Sensor", key = "#name")
//    public void deleteSensorByName(String name){
//        sensorRepository.deleteByName(name);
//    }

    private void isAlreadyExist(SensorDto sensorDto) {
        if (getSensorByName(sensorDto.getName()) != null) {
            log.warn("Sensor {} already exist", sensorDto.getName());
            throw new EntityExistsException("Sensor with name " + sensorDto.getName() + " already exists");
        }
    }


}
