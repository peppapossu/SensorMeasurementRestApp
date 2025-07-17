package ru.kir.sm.sensormeasurementrestapp.repositories;

import ru.kir.sm.sensormeasurementrestapp.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Long> {

    @Query(value = "select count(*) from measurement where raining is true", nativeQuery = true)
    int findAllByRainingIsTrue();
}
