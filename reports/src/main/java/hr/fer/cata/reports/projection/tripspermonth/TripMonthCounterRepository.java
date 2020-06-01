package hr.fer.cata.reports.projection.tripspermonth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripMonthCounterRepository extends JpaRepository<TripMonthCounter, Integer> {
}
