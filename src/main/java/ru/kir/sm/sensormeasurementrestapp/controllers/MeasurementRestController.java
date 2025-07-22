package ru.kir.sm.sensormeasurementrestapp.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kir.sm.sensormeasurementrestapp.dto.MeasurementDto;
import ru.kir.sm.sensormeasurementrestapp.kafka.KafkaProducer;
import ru.kir.sm.sensormeasurementrestapp.services.MeasurementService;

import java.util.List;

@RestController
@RequestMapping("/measurements")
@AllArgsConstructor
public class MeasurementRestController {
    private final MeasurementService measurementService;
    private final KafkaProducer kafkaProducer;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addKafka(@Valid @RequestBody MeasurementDto measurementDto) {
        kafkaProducer.send(measurementDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MeasurementDto> getMeasurements() {
        return measurementService.get();
    }

    @GetMapping("/rainyDaysCount")
    @ResponseStatus(HttpStatus.OK)
    public int getRainyDaysCount() {
        return measurementService.rainyDaysCount();
    }
}
