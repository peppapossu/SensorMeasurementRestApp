package ru.kir.sm.sensormeasurementrestapp.service.impl;

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
import ru.kir.sm.sensormeasurementrestapp.model.Measurement;
import ru.kir.sm.sensormeasurementrestapp.model.Sensor;
import ru.kir.sm.sensormeasurementrestapp.repositorie.MeasurementRepository;
import ru.kir.sm.sensormeasurementrestapp.service.MeasurementService;
import ru.kir.sm.sensormeasurementrestapp.service.SensorService;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MeasurementServiceImpl implements MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final MeasurementMapper mapper;
    private final SensorService sensorService;
    private final KafkaProducer kafkaProducer;

    @Transactional
    public void add(MeasurementDto measurementDto) {
        String sensorName = measurementDto.sensor().name();

        sensorExist(measurementDto.sensor());

        Sensor sensor = sensorService.getSensorByName(sensorName);

        Measurement measurement = mapper.toEntity(measurementDto);
        measurement.setSensor(sensor);

        measurementRepository.save(measurement);
        log.info("Saved new measurement for Sensor name:'{}'", sensorName);
    }

    public void addKafka(MeasurementDto measurementDto) {
        String sensorName = measurementDto.sensor().name();
        sensorExist(measurementDto.sensor());
        kafkaProducer.send("measurement", sensorName, measurementDto);
    }

    public MeasurementsResponse get() {
        return new MeasurementsResponse(mapper.toDto(measurementRepository.findAll()));
    }

    public int rainyDaysCount() {
        return measurementRepository.findAllByRainingIsTrue();
    }

    private void sensorExist(SensorDto sensorDto) {
        String sensorName = sensorDto.name();
        if (!sensorService.isAlreadyExist(sensorDto)){
            log.error("Sensor name:'{}' does not exist", sensorName);
            throw new EntityNotFoundException("Sensor name:'" + sensorName + "' does not exist");
        }
    }
}
