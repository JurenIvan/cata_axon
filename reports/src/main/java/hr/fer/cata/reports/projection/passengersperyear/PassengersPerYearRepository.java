package hr.fer.cata.reports.projection.passengersperyear;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengersPerYearRepository extends JpaRepository<PassengerPerYear, Integer> {
}
