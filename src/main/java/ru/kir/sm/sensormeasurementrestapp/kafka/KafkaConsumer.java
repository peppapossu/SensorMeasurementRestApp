package ru.kir.sm.sensormeasurementrestapp.kafka;

import ru.kir.sm.sensormeasurementrestapp.mapper.StringJsonMapper;
import ru.kir.sm.sensormeasurementrestapp.services.MeasurementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.kir.sm.sensormeasurementrestapp.dto.MeasurementDto;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumer {
    private final MeasurementService measurementService;
    private final StringJsonMapper stringJsonMapper;

    @KafkaListener(topics = "my-topic", groupId = "my-group")
    public void listen(String topic, String message) {
        measurementService.add(stringJsonMapper.stringToObject(message, MeasurementDto.class));
        log.info("Received Message from topic {}", topic);
    }

//    @KafkaListener(topics = "my-topic", groupId = "my-group")
//    public void listen(MeasurementDto message) {
//        log.info("Received Message from my-topic: {}", message);
//        measurementService.add(message);
//    }
}
