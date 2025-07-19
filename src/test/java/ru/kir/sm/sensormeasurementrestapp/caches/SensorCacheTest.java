package ru.kir.sm.sensormeasurementrestapp.caches;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import ru.kir.sm.sensormeasurementrestapp.dto.SensorDto;


@SpringBootTest
class SensorCacheTest {

    @Autowired
    private RedisTemplate<String, SensorDto> redisTemplate;

    @Test
    public void test() {
        SensorDto sensor = new SensorDto();
        sensor.setName("test");
        redisTemplate.opsForValue().set("sensor:1", sensor); // если тут ошибка — проблема в сериализации
        System.out.printf("sensor:1=%s\n", redisTemplate.opsForValue().get("sensor:1"));
    }
}