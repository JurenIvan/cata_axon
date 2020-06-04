package hr.fer.gateway.controllers;

import hr.fer.connector.dto.reports.TripMonth;
import hr.fer.connector.dto.reports.UserHistoryDto;
import hr.fer.connector.dto.reports.YearPassengerCount;
import hr.fer.connector.interfaces.ReportsREST;
import hr.fer.gateway.services.ContextService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReportsGatewayController {

    private final ReportsREST reportsREST;
    private final ContextService contextService;

    @GetMapping("/reports/trips-per-month/{year}")
    List<TripMonth> getTripsPerMonth(@PathVariable("year") Integer year) {
        return reportsREST.getTripsPerMonth(year, contextService.getLoggedIn());
    }

    @GetMapping("/reports/passengers-per-year")
    List<YearPassengerCount> getNumberOfPassengersPerYearSuccess() {
        return reportsREST.getNumberOfPassengersPerYearSuccess(contextService.getLoggedIn());
    }

    @GetMapping("/reports/passenger-history/{userId}")
    UserHistoryDto getUserHistory(@PathVariable Long userId) {
        return reportsREST.getUserHistory(userId, contextService.getLoggedIn());
    }
}
