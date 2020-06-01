package hr.fer.cata.reports.service;

import hr.fer.cata.reports.projection.passengersperyear.PassengersPerYearProjection;
import hr.fer.cata.reports.projection.tripspermonth.TripMonthCounterProjection;
import hr.fer.cata.reports.projection.userhistory.UserHistory;
import hr.fer.cata.reports.projection.userhistory.UserHistoryProjection;
import hr.fer.connector.dto.reports.TripMonth;
import hr.fer.connector.dto.reports.YearPassengerCount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

@Service
@RequiredArgsConstructor
public class ReportsService {

    public final TripMonthCounterProjection tripMonthCounterProjection;
    public final PassengersPerYearProjection passengersPerYearProjection;
    public final UserHistoryProjection userHistoryProjection;

    public List<TripMonth> getTripsPerMonth(Integer year) {
        List<Integer> tripPerMonths = tripMonthCounterProjection.find(year).getPassengerCountPerMonth();
        return range(0, 12).mapToObj(e -> new TripMonth(tripPerMonths.get(e), e + 1)).collect(toList());
    }

    public List<YearPassengerCount> getNumberOfPassengersPerYear() {
        return passengersPerYearProjection.getNumberOfPassengersPerYear();
    }

    public UserHistory getUserHistory(Long userId) {
        return userHistoryProjection.getHistoryForUser(userId);
    }
}
