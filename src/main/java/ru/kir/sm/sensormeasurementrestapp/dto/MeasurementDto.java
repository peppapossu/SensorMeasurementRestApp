package ru.kir.sm.sensormeasurementrestapp.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record MeasurementDto(

        @NotNull(message = "Value must be not null")
        @DecimalMin(value = "-100.0", inclusive = false, message = "Value must be more than -100")
        @DecimalMax(value = "100.0", inclusive = true, message = "Value must be less then 100")
        Double value,

        @NotNull(message = "Raining must be not null")
        Boolean raining,

        @NotNull
        @Valid
        SensorDto sensor) {
}
