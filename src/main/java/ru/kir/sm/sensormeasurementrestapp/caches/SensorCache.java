package ru.kir.sm.sensormeasurementrestapp.caches;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public Sensor getSensorByName(String name){
        log.info("getSensorByName: name={}", name);
        return sensorRepository.getSensorByName(name).orElse(null);
    }
}
