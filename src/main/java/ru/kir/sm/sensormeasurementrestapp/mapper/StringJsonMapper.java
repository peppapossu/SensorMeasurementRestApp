package ru.kir.sm.sensormeasurementrestapp.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StringJsonMapper {
    private final ObjectMapper mapper;

    public <T> T stringToObject(String jsonString, Class<T> classType) {
        try {
            return mapper.readValue(jsonString, classType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing JSON", e);
        }
    }
}
