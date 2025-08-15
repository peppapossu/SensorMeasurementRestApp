package ru.kir.sm.sensormeasurementrestapp.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kir.sm.sensormeasurementrestapp.dto.MeasurementDto;
import ru.kir.sm.sensormeasurementrestapp.dto.MeasurementsResponse;
import ru.kir.sm.sensormeasurementrestapp.services.MeasurementService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/measurements")
@RequiredArgsConstructor
public class MeasurementRestController {

    private final MeasurementService measurementService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@Valid @RequestBody MeasurementDto measurementDto) {
        measurementService.add(measurementDto);
    }

    @PostMapping("/add/kafka")
    @ResponseStatus(HttpStatus.CREATED)
    public void addKafka(@Valid @RequestBody MeasurementDto measurementDto) {
        measurementService.addKafka(measurementDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public MeasurementsResponse getMeasurements() {
        return measurementService.get();
    }

    @GetMapping("/rainyDaysCount")
    @ResponseStatus(HttpStatus.OK)
    public int getRainyDaysCount() {
        return measurementService.rainyDaysCount();
    }
}
