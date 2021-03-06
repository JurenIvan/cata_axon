package hr.fer.connector.interfaces;

import hr.fer.connector.dto.trips.CancelTripDto;
import hr.fer.connector.dto.trips.TripDetailsDto;
import hr.fer.connector.dto.trips.TripDto;
import hr.fer.connector.dto.trips.TripPreviewDto;
import hr.fer.connector.model.ContextHolder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("cata-trips")
public interface TripsREST {

    @PostMapping("/create-trip")
    String createTrip(@RequestBody TripDto tripDto);

    @PostMapping("/edit-trip")
    void editTrip(@RequestBody TripDto tripDto);

    @PostMapping("/join-trip/{tripId}")
    void joinTrip(@PathVariable("tripId") String tripId, @RequestBody ContextHolder contextHolder);

    @PostMapping("/leave-trip/{tripId}")
    void leaveTrip(@PathVariable("tripId") String tripId, @RequestBody ContextHolder contextHolder);

    @PostMapping("/dissmiss/{tripId}")
    void cancelTrip(@PathVariable("tripId") String tripId, @RequestBody CancelTripDto cancelTripDto);

    @PostMapping("/accept-user/{tripId}/{userId}")
    void acceptUserToTrip(@PathVariable("tripId") String tripId, @PathVariable("userId") Long userId, @RequestBody ContextHolder contextHolder);

    @PostMapping("/deny-user/{tripId}/{userId}")
    void denyUserToTrip(@PathVariable("tripId") String tripId, @PathVariable("userId") Long userId, @RequestBody ContextHolder contextHolder);

    @GetMapping("/view-trips")
    List<TripPreviewDto> viewTrips();

    @GetMapping("/view-trip/{tripId}")
    TripDetailsDto viewTrip(@PathVariable("tripId") String tripId, @RequestBody ContextHolder contextHolder);

}

