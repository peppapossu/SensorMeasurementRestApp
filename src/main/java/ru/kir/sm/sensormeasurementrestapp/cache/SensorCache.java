package ru.kir.sm.sensormeasurementrestapp.cache;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import ru.kir.sm.sensormeasurementrestapp.models.Sensor;
import ru.kir.sm.sensormeasurementrestapp.repositories.SensorRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class SensorCache {
    private final SensorRepository sensorRepository;

    @Cacheable(value = "Sensor", key = "#name")
    public Sensor getSensorByName(String name) {
        log.info("Cache didn't find, DB request: name={}", name);
        return sensorRepository.getSensorByName(name).orElse(null);
    }

    @CacheEvict(value = "Sensor", key = "#name")
    public void deleteSensorByName(String name) {
        log.info("Delete Sensor by name from the cache: name={}", name);
        sensorRepository.deleteByName(name);
    }

    @CachePut(value = "Sensor", key = "#name")
    public Sensor addSensor(String name, Sensor sensor) {
        log.info("Cache updated with: name={}", name);
        return sensorRepository.save(sensor);
    }
}
