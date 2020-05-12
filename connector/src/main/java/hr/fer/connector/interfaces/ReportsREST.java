package hr.fer.connector.interfaces;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("cata-reports")
public interface ReportsREST {

//    @GetMapping("/reports/trips-per-month/{year}")
//    List<TripMonth> getTripsPerMonth(@PathVariable Integer year);
//
//    @GetMapping("/reports/trips-per-year")
//    List<TripYear> getTripsPerMonthPerYear();
//
//    @GetMapping("/reports/passengers-per-year/success")
//    List<YearPassenger> getNumberOfPassengersPerYear();
//
//    @GetMapping("/reports/passengers-per-year/quit")
//    List<YearPassenger> getNumberOfPassengersPerYear();
//
//    @GetMapping("/reports/passengers-change-mind")
//    Integer getSuccessfulTripsAfterOnceQuit();
}
