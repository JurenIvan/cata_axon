package hr.fer.gateway.controllers;

import hr.fer.connector.dto.reports.TripMonth;
import hr.fer.connector.dto.reports.UserHistoryDto;
import hr.fer.connector.dto.reports.YearPassengerCount;
import hr.fer.connector.interfaces.ReportsREST;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReportsGatewayController {

    private final ReportsREST reportsREST;

    @GetMapping("/reports/trips-per-month/{year}")
    List<TripMonth> getTripsPerMonth(@PathVariable("year") Integer year, @RequestHeader(value = "Authorization") String accessToken) {
        return reportsREST.getTripsPerMonth(year, accessToken);
    }

    @GetMapping("/reports/passengers-per-year")
    List<YearPassengerCount> getNumberOfPassengersPerYearSuccess(@RequestHeader(value = "Authorization") String accessToken) {
        return reportsREST.getNumberOfPassengersPerYearSuccess(accessToken);
    }

    @GetMapping("/reports/passenger-history/{userId}")
    UserHistoryDto getUserHistory(@PathVariable Long userId, @RequestHeader(value = "Authorization") String accessToken) {
        return reportsREST.getUserHistory(userId, accessToken);
    }
}
