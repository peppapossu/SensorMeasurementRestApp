package ru.kir.sm.sensormeasurementrestapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = "measurements")
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "sensor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Measurement> measurements =  new ArrayList<>();

    public void addMeasurement(Measurement measurement) {
        measurements.add(measurement);
        measurement.setSensor(this);
    }

    public void removeMeasurement(Measurement measurement) {
        measurements.remove(measurement);
        measurement.setSensor(null);
    }
}
