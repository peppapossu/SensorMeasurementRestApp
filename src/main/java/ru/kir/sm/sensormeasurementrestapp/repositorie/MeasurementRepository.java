package ru.kir.sm.sensormeasurementrestapp.repositorie;

import ru.kir.sm.sensormeasurementrestapp.model.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Long> {

    @Query(value = "select count(*) from measurement where raining is true", nativeQuery = true)
    int findAllByRainingIsTrue();
}
