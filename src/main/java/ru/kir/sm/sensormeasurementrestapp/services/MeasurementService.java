package ru.kir.sm.sensormeasurementrestapp.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kir.sm.sensormeasurementrestapp.dto.MeasurementDto;
import ru.kir.sm.sensormeasurementrestapp.dto.MeasurementsResponse;
import ru.kir.sm.sensormeasurementrestapp.dto.SensorDto;
import ru.kir.sm.sensormeasurementrestapp.kafka.KafkaProducer;
import ru.kir.sm.sensormeasurementrestapp.mapper.MeasurementMapper;
import ru.kir.sm.sensormeasurementrestapp.models.Measurement;
import ru.kir.sm.sensormeasurementrestapp.models.Sensor;
import ru.kir.sm.sensormeasurementrestapp.repositories.MeasurementRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final MeasurementMapper mapper;
    private final SensorService sensorService;
    private final KafkaProducer kafkaProducer;

    @Transactional
    public void add(MeasurementDto measurementDto) {
        String sensorName = measurementDto.sensor().name();

        isSensorExist(measurementDto.sensor());

        Sensor sensor = sensorService.getSensorByName(sensorName);

        Measurement measurement = mapper.toEntity(measurementDto);
        measurement.setSensor(sensor);

        measurementRepository.save(measurement);
        log.info("Saved new measurement for sensor '{}'", sensorName);
    }

    public void addKafka(MeasurementDto measurementDto) {
        String sensorName = measurementDto.sensor().name();
        isSensorExist(measurementDto.sensor());
        kafkaProducer.send("measurement", sensorName, measurementDto);
    }

    public MeasurementsResponse get() {
        return new MeasurementsResponse(mapper.toDto(measurementRepository.findAll()));
    }

    public int rainyDaysCount() {
        return measurementRepository.findAllByRainingIsTrue();
    }

    private void isSensorExist(SensorDto sensorDto) {
        String sensorName = sensorDto.name();
        if (!sensorService.isAlreadyExist(sensorDto)){
            log.error("Sensor '{}' does not exist", sensorName);
            throw new EntityNotFoundException("Sensor '" + sensorName + "' does not exist");
        }
    }
}
