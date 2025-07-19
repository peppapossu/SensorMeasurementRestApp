package ru.kir.sm.sensormeasurementrestapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SensorDto {

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 30)
    private String name;
}
