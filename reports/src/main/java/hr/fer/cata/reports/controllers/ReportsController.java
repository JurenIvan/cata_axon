package hr.fer.cata.reports.controllers;

import hr.fer.cata.reports.service.ReportsService;
import hr.fer.connector.dto.reports.TripMonth;
import hr.fer.connector.dto.reports.UserHistoryDto;
import hr.fer.connector.dto.reports.YearPassengerCount;
import hr.fer.connector.interfaces.ReportsREST;
import hr.fer.connector.model.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static hr.fer.connector.model.Role.ADMIN;

@RestController
@RequiredArgsConstructor
public class ReportsController implements ReportsREST {

    private final ReportsService reportsService;

    @Override
    public List<TripMonth> getTripsPerMonth(Integer year, @RequestBody ContextHolder contextHolder) {
        if (!contextHolder.getRole().equals(ADMIN))
            throw new IllegalArgumentException();

        return reportsService.getTripsPerMonth(year);
    }

    @Override
    public List<YearPassengerCount> getNumberOfPassengersPerYearSuccess(@RequestBody ContextHolder contextHolder) {
        if (!contextHolder.getRole().equals(ADMIN))
            throw new IllegalArgumentException();

        return reportsService.getNumberOfPassengersPerYear();
    }

    @Override
    public UserHistoryDto getUserHistory(Long userId, @RequestBody ContextHolder contextHolder) {
        if (!contextHolder.getRole().equals(ADMIN))
            throw new IllegalArgumentException();

        return reportsService.getUserHistory(userId).toDto();
    }
}
