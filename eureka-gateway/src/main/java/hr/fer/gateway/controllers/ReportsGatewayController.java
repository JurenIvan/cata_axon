package hr.fer.gateway.controllers;

import hr.fer.connector.dto.reports.TripMonth;
import hr.fer.connector.dto.reports.TripYear;
import hr.fer.connector.dto.reports.YearPassengerCount;
import hr.fer.connector.interfaces.ReportsREST;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReportsGatewayController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportsGatewayController.class);
    private final ReportsREST reportsREST;

    @GetMapping("/reports/trips-per-month/{year}")
    List<TripMonth> getTripsPerMonth(@PathVariable Integer year, @RequestHeader(value = "Authorization") String accessToken) {
        LOGGER.info("getTripsPerMonth " + year);
        return reportsREST.getTripsPerMonth(year, accessToken);
    }

    @GetMapping("/reports/trips-per-year")
    List<TripYear> getTripsPerMonthPerYear(@RequestHeader(value = "Authorization") String accessToken) {
        LOGGER.info("getTripsPerMonthPerYear");
        return reportsREST.getTripsPerMonthPerYear(accessToken);
    }

    @GetMapping("/reports/passengers-per-year/success")
    List<YearPassengerCount> getNumberOfPassengersPerYearSuccess(@RequestHeader(value = "Authorization") String accessToken) {
        LOGGER.info("getNumberOfPassengersPerYearSuccess");
        return reportsREST.getNumberOfPassengersPerYearSuccess(accessToken);
    }

    @GetMapping("")
    List<YearPassengerCount> getNumberOfPassengersPerYearQuitted(@RequestHeader(value = "Authorization") String accessToken) {
        LOGGER.info("getNumberOfPassengersPerYearQuitted");
        return reportsREST.getNumberOfPassengersPerYearQuitted(accessToken);
    }

    @GetMapping("/reports/passengers-change-mind")
    Integer getSuccessfulTripsAfterOnceQuit(@RequestHeader(value = "Authorization") String accessToken) {
        LOGGER.info("getSuccessfulTripsAfterOnceQuit");
        return reportsREST.getSuccessfulTripsAfterOnceQuit(accessToken);
    }
}
