package ru.kir.sm.sensormeasurementrestapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kir.sm.sensormeasurementrestapp.dto.MeasurementDto;
import ru.kir.sm.sensormeasurementrestapp.dto.MeasurementsResponse;
import ru.kir.sm.sensormeasurementrestapp.service.MeasurementService;

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
