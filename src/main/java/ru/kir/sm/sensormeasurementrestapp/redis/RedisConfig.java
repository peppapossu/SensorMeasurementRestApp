package ru.kir.sm.sensormeasurementrestapp.redis;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import ru.kir.sm.sensormeasurementrestapp.models.Sensor;

@Configuration
public class RedisConfig {

    @Bean
    @Primary
    public RedisTemplate<String, Sensor> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Sensor> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        // JSON-сериализация для значений
        Jackson2JsonRedisSerializer<Sensor> serializer = new Jackson2JsonRedisSerializer<>(Sensor.class);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.activateDefaultTyping(
                LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.PROPERTY
        );
        serializer.setObjectMapper(objectMapper);

        // Сериализаторы
        template.setValueSerializer(serializer);
        template.setKeySerializer(new StringRedisSerializer());

        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);

        template.afterPropertiesSet();
        return template;
    }
}
