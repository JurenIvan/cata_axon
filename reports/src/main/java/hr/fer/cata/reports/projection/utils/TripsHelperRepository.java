package hr.fer.cata.reports.projection.utils;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripsHelperRepository extends JpaRepository<TripTime, String> {
}
