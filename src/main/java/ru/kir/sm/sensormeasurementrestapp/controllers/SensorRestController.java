package ru.kir.sm.sensormeasurementrestapp.controllers;

import lombok.RequiredArgsConstructor;
import ru.kir.sm.sensormeasurementrestapp.dto.SensorDto;
import ru.kir.sm.sensormeasurementrestapp.services.SensorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sensors")
@RequiredArgsConstructor
public class SensorRestController {
    private final SensorService sensorService;

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public void registration(@Valid @RequestBody SensorDto sensorDto) {
        sensorService.addSensor(sensorDto);
    }
}
