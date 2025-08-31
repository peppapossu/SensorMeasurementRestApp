package ru.kir.sm.sensormeasurementrestapp.kafka;

import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import ru.kir.sm.sensormeasurementrestapp.mapper.StringJsonMapper;
import ru.kir.sm.sensormeasurementrestapp.service.MeasurementService;
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

    @KafkaListener(topics = "measurement", groupId = "my-group")
    public void listen(String message, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic, Acknowledgment ack) {
        log.info("Received Message from topic '{}'", topic);
            measurementService.add(stringJsonMapper.stringToObject(message, MeasurementDto.class));
            ack.acknowledge();
    }
}
