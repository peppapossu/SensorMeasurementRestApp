package ru.kir.sm.sensormeasurementrestapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

public record SensorDto(
        @NotBlank(message = "Name cannot be blank")
        @Size(min = 3, max = 30, message = "Name must be between 3 and 30 symbols")
        String name) {
}
