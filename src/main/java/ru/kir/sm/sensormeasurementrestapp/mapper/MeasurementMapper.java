package ru.kir.sm.sensormeasurementrestapp.mapper;

import ru.kir.sm.sensormeasurementrestapp.dto.MeasurementDto;
import ru.kir.sm.sensormeasurementrestapp.model.Measurement;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MeasurementMapper {

    Measurement toEntity(MeasurementDto dto);

    MeasurementDto toDto(Measurement entity);

    List<MeasurementDto> toDto(List<Measurement> measurements);

    List<Measurement> toEntity(List<Measurement> measurements);
}
