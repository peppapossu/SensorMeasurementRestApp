package ru.kir.sm.sensormeasurementrestapp.repositories;

import ru.kir.sm.sensormeasurementrestapp.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {

    Optional<Sensor> getSensorByName(String name);

    void deleteByName(String name);
}
