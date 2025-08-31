package ru.kir.sm.sensormeasurementrestapp.validator;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kir.sm.sensormeasurementrestapp.dto.SensorDto;
import ru.kir.sm.sensormeasurementrestapp.mapper.SensorMapper;
import ru.kir.sm.sensormeasurementrestapp.service.SensorService;

@Component
@AllArgsConstructor
public class SensorDtoValidator implements Validator {
    private final SensorMapper sensorMapper;
    private final SensorService sensorService;

    @Override
    public boolean supports(Class<?> clazz) {
        return SensorDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorDto sensorDto = (SensorDto) target;
        if (sensorService.getSensorByName(sensorMapper.toEntity(sensorDto).getName()) != null) {
            String message = "Sensor name:'" + sensorDto.name() + "' already exists";
            errors.rejectValue("name","", message);
        }
    }
}
