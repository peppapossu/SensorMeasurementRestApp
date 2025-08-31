package ru.kir.sm.sensormeasurementrestapp.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = "sensor")
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;

    @Column(nullable = false)
    Double value;

    @Column(nullable = false)
    Boolean raining;

    @ManyToOne
    @JoinColumn(name = "sensor_id", nullable = false, referencedColumnName = "id")
//    @JsonIgnore
    Sensor sensor;

    @CreationTimestamp
    @Column(updatable = false)
    LocalDateTime time;
}
