package ru.kir.sm.sensormeasurementrestapp.repositorie;

import ru.kir.sm.sensormeasurementrestapp.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {

    Optional<Sensor> getSensorByName(String name);

    void deleteByName(String name);

//    @Query("select new ru.kir.sm.sensormeasurementrestapp.dto.SensorDto(s.name) from Sensor s")
//    MeasurementDto getMeasurementByName(String name);
}
