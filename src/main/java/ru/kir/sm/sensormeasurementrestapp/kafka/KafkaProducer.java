package ru.kir.sm.sensormeasurementrestapp.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.kir.sm.sensormeasurementrestapp.dto.MeasurementDto;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, MeasurementDto> kafkaTemplate;

    public void send(MeasurementDto message) {
        log.info("Sending message to Kafka: {}", message);
        kafkaTemplate.send("my-topic", message);
    }
}
