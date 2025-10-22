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
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private Double value;

    @Column(nullable = false)
    private Boolean raining;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sensor_id", nullable = false, referencedColumnName = "id")
//    @JsonIgnore
    private Sensor sensor;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private LocalDateTime created_at;
}
