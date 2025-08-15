package ru.kir.sm.sensormeasurementrestapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    Double value;

    @Column(nullable = false)
    Boolean raining;

    @ManyToOne
    @JoinColumn(name = "sensor_id", nullable = false
//            , referencedColumnName = "name"
    )
    @JsonIgnore
    Sensor sensor;

    @CreationTimestamp
    @Column(updatable = false)
    LocalDateTime time;
}
