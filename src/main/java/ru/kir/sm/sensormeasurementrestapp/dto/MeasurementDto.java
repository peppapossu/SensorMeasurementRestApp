package ru.kir.sm.sensormeasurementrestapp.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeasurementDto {

    @NotNull(message = "value must be not null")
    @DecimalMin(value = "-100.0", inclusive = false, message = "Value must be more than -100")
    @DecimalMax(value = "100.0", inclusive = true, message = "Value must be less then 100")
    private Double value;

    @NotNull(message = "raining must be not null")
    private Boolean raining;

    @NotNull
    @Valid
    private SensorDto sensor;
}
