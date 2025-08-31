package ru.kir.sm.sensormeasurementrestapp.cache;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.data.redis.cache.RedisCacheManager;
import redis.embedded.RedisServer;
import ru.kir.sm.sensormeasurementrestapp.model.Sensor;
import ru.kir.sm.sensormeasurementrestapp.repositorie.SensorRepository;
import ru.kir.sm.sensormeasurementrestapp.service.SensorService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

import static org.mockito.Mockito.when;

//@ActiveProfiles("test")
@Slf4j
@SpringBootTest
//@Import({TwoLevelCacheTestConfig.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TwoLevelCacheTest {

    @Autowired
    private SensorService sensorService;

    @Autowired
//    @Qualifier("cacheManagerTest")
    private CacheManager cacheManager;

    private RedisServer redisServer;

    @Autowired
    private CaffeineCacheManager caffeineCacheManager;

    @Autowired
    private RedisCacheManager redisCacheManager;

    @MockBean
    private SensorRepository sensorRepository;


    @BeforeAll
    void startRedis() throws IOException {
        redisServer = new RedisServer(6379);
        redisServer.start();
    }

    @AfterAll
    void stopRedis() throws IOException {
        if (redisServer != null) {
            redisServer.stop();
        }
    }

    @Test
    void testCaffeineL1Cache() {
        final String TEST_NAME = "string123";

        Sensor sensor = new Sensor(1, TEST_NAME, new ArrayList<>() {
        });
        when(sensorRepository.getSensorByName(TEST_NAME))
                .thenReturn(Optional.of(sensor));

        var caffeineCache = Objects.requireNonNull(caffeineCacheManager.getCache("Sensor"));
        log.info("L1cache before, content = {} , name = {}", caffeineCache.getName(), caffeineCache.get(TEST_NAME));

        String value1 = sensorService.getSensorByName(TEST_NAME).getName();
        String value2 = sensorService.getSensorByName(TEST_NAME).getName();

        caffeineCache = Objects.requireNonNull(caffeineCacheManager.getCache("Sensor"));
        log.info("L1cache after, content = {} , name = {}", caffeineCache.getName(), caffeineCache.get(TEST_NAME));

        // Должно вернуть из L1 (Caffeine), значения равны
        log.info("testCaffeineL1Cache {} ", value1);
        Assertions.assertEquals(value1, value2);
    }

    @Test
    void testRedisL2Cache() {

        final String TEST_NAME = "string1";

        Sensor sensor = new Sensor(1,TEST_NAME, new ArrayList<>());
        when(sensorRepository.getSensorByName(TEST_NAME))
                .thenReturn(Optional.of(sensor));

        var caffeineCache = Objects.requireNonNull(caffeineCacheManager.getCache("Sensor"));
        log.info("L1cache before content = {} , name = {}", caffeineCache.getName(), caffeineCache.get(TEST_NAME));

        // "прогреваем" кэш
        String value1 = sensorService.getSensorByName(TEST_NAME).getName();

        caffeineCache = Objects.requireNonNull(caffeineCacheManager.getCache("Sensor"));
        log.info("L1cache content = {} , name = {}", caffeineCache.getName(), caffeineCache.get(TEST_NAME));

        String value3 = Objects.requireNonNull(Objects.requireNonNull(redisCacheManager.getCache("Sensor")).get(TEST_NAME)).toString();
        log.info("L2cache content = {}", value3);

        // Чистим L1 (Caffeine)
        Objects.requireNonNull(caffeineCacheManager.getCache("Sensor")).invalidate();
        caffeineCache = Objects.requireNonNull(caffeineCacheManager.getCache("Sensor"));
        log.info("L1cache after clear content = {}, name = {}", caffeineCache.getName(), caffeineCache.get(TEST_NAME));

        // Должно прийти из L2 (Redis), а не генериться новое
        String value2 = sensorService.getSensorByName(TEST_NAME).getName();

        Assertions.assertEquals(value1, value2);
    }
}
