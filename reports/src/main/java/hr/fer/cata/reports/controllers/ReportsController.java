package hr.fer.cata.reports.controllers;

import hr.fer.cata.reports.service.ReportsService;
import hr.fer.connector.dto.reports.TripMonth;
import hr.fer.connector.dto.reports.UserHistoryDto;
import hr.fer.connector.dto.reports.YearPassengerCount;
import hr.fer.connector.interfaces.ReportsREST;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReportsController implements ReportsREST {

    private final ReportsService reportsService;

    @Override
    public List<TripMonth> getTripsPerMonth(Integer year, String accessToken) {
        return reportsService.getTripsPerMonth(year);
    }

    @Override
    public List<YearPassengerCount> getNumberOfPassengersPerYearSuccess(String accessToken) {
        return reportsService.getNumberOfPassengersPerYear();
    }

    @Override
    public UserHistoryDto getUserHistory(Long userId, String accessToken) {
        return reportsService.getUserHistory(userId).toDto();
    }
}
