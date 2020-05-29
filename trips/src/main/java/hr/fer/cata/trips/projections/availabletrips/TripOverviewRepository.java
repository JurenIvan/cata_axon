package hr.fer.cata.trips.projections.availabletrips;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository()
public interface TripOverviewRepository extends JpaRepository<TripOverview, String> {
}
