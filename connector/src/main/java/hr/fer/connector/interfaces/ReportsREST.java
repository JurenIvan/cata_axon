package hr.fer.connector.interfaces;

import hr.fer.connector.dto.reports.TripMonth;
import hr.fer.connector.dto.reports.TripYear;
import hr.fer.connector.dto.reports.YearPassengerCount;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient("cata-reports")
public interface ReportsREST {

    @GetMapping("/reports/trips-per-month/{year}")
    List<TripMonth> getTripsPerMonth(@PathVariable Integer year, @RequestHeader(value = "Authorization") String accessToken);

    @GetMapping("/reports/trips-per-year")
    List<TripYear> getTripsPerMonthPerYear(@RequestHeader(value = "Authorization") String accessToken);

    @GetMapping("/reports/passengers-per-year/success")
    List<YearPassengerCount> getNumberOfPassengersPerYearSuccess(@RequestHeader(value = "Authorization") String accessToken);

    @GetMapping("/reports/passengers-per-year/quit")
    List<YearPassengerCount> getNumberOfPassengersPerYearQuitted(@RequestHeader(value = "Authorization") String accessToken);

    @GetMapping("/reports/passengers-change-mind")
    Integer getSuccessfulTripsAfterOnceQuit(@RequestHeader(value = "Authorization") String accessToken);
}
