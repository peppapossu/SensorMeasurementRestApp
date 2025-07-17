package ru.kir.sm.sensormeasurementrestapp.redis;

import ru.kir.sm.sensormeasurementrestapp.dto.SensorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final RedisTemplate<String, SensorDto> sensorDtoRedisTemplate;

    public void saveSensor(String key, SensorDto sensor) {
        sensorDtoRedisTemplate.opsForValue().set(key, sensor);
    }

    public SensorDto getSensor(String key) {
        return sensorDtoRedisTemplate.opsForValue().get(key);
    }

    public void deleteSensor(String key) {
        sensorDtoRedisTemplate.delete(key);
    }
}
