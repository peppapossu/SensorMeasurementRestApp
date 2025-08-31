package ru.kir.sm.sensormeasurementrestapp.mapper;

import ru.kir.sm.sensormeasurementrestapp.dto.SensorDto;
import ru.kir.sm.sensormeasurementrestapp.model.Sensor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SensorMapper{

    Sensor toEntity(SensorDto dto);

    SensorDto toDto(Sensor entity);
}
