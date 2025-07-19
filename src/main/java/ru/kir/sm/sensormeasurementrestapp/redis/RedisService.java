package ru.kir.sm.sensormeasurementrestapp.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.kir.sm.sensormeasurementrestapp.models.Sensor;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final RedisTemplate<String, Sensor> sensorRedisTemplate;

    public void saveSensor(String key, Sensor sensor) {
        sensorRedisTemplate.opsForValue().set(key, sensor);
    }

    public Sensor getSensor(String key) {
        return sensorRedisTemplate.opsForValue().get(key);
    }

    public void deleteSensor(String key) {
        sensorRedisTemplate.delete(key);
    }
}
