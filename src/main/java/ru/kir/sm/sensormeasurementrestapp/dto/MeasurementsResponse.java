package ru.kir.sm.sensormeasurementrestapp.dto;

import java.util.List;

public record MeasurementsResponse(List<MeasurementDto> measurements) {
}
