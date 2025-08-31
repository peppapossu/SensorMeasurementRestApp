package ru.kir.sm.sensormeasurementrestapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.WebDataBinder;
import ru.kir.sm.sensormeasurementrestapp.dto.SensorDto;
import ru.kir.sm.sensormeasurementrestapp.service.SensorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kir.sm.sensormeasurementrestapp.validator.SensorDtoValidator;

@RestController
@RequestMapping("/sensors")
@RequiredArgsConstructor
public class SensorRestController {
    private final SensorService sensorService;
    private final SensorDtoValidator sensorDtoValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(sensorDtoValidator);
    }

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public void registration(@Valid @RequestBody SensorDto sensorDto) {
        sensorService.addSensor(sensorDto);
    }
}
