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

    //    @Retryable(
//            retryFor = {KafkaException.class},
//            maxAttempts = 5,
//            backoff = @Backoff(delay = 5000),
//            recover = "recover"
//    )
    public void send(String topic, String key, MeasurementDto message) {
            log.info("Trying send message to Kafka: '{}'", message);
            kafkaTemplate.send(topic, key, message);
            log.info("Message successfully sent to Kafka: '{}'", message);
    }
//    @Recover
//    public void recover(KafkaException cause, String topic, String key, MeasurementDto message) {
//        log.info("Recover Kafka message to Kafka: {}", message);
//    }
}
