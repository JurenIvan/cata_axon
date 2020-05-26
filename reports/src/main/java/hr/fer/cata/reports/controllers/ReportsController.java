package hr.fer.cata.reports.controllers;

import hr.fer.connector.dto.reports.TripMonth;
import hr.fer.connector.dto.reports.TripYear;
import hr.fer.connector.dto.reports.YearPassengerCount;
import hr.fer.connector.interfaces.ReportsREST;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReportsController implements ReportsREST {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportsController.class);

    @Override
    public List<TripMonth> getTripsPerMonth(Integer year, String accessToken) {
        LOGGER.info("getTripsPerMonth");
        return null;
    }

    @Override
    public List<TripYear> getTripsPerMonthPerYear(String accessToken) {
        LOGGER.info("getTripsPerMonthPerYear");
        return null;
    }

    @Override
    public List<YearPassengerCount> getNumberOfPassengersPerYearSuccess(String accessToken) {
        LOGGER.info("getNumberOfPassengersPerYearSuccess");
        return null;
    }

    @Override
    public List<YearPassengerCount> getNumberOfPassengersPerYearQuitted(String accessToken) {
        LOGGER.info("getNumberOfPassengersPerYearQuitted");
        return null;
    }

    @Override
    public Integer getSuccessfulTripsAfterOnceQuit(String accessToken) {
        LOGGER.info("getSuccessfulTripsAfterOnceQuit");
        return null;
    }
}
