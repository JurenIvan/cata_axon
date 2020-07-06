package hr.fer.connector.interfaces;

import hr.fer.connector.dto.reports.TripMonth;
import hr.fer.connector.dto.reports.UserHistoryDto;
import hr.fer.connector.dto.reports.YearPassengerCount;
import hr.fer.connector.model.ContextHolder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("cata-reports")
public interface ReportsREST {

    @PostMapping("/reports/trips-per-month/{year}")
    List<TripMonth> getTripsPerMonth(@PathVariable("year") Integer year, @RequestBody ContextHolder contextHolder);

    @PostMapping("/reports/passengers-per-year/success")
    List<YearPassengerCount> getNumberOfPassengersPerYearSuccess(@RequestBody ContextHolder contextHolder);

    @PostMapping("/reports/passenger-history/{userId}")
    UserHistoryDto getUserHistory(@PathVariable("userId") Long userId, @RequestBody ContextHolder contextHolder);
}
