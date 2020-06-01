package hr.fer.connector.interfaces;

import hr.fer.connector.dto.trips.CancelTripDto;
import hr.fer.connector.dto.trips.TripDetailsDto;
import hr.fer.connector.dto.trips.TripDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("cata-trips")
public interface TripsREST {

    @PostMapping("/create-trip/")
    String createTrip(@RequestBody TripDto tripDto, @RequestHeader(value = "Authorization") String accessToken);

    @PostMapping("/edit-trip")
    void editTrip(@RequestBody TripDto tripDto, @RequestHeader(value = "Authorization") String accessToken);

    @PostMapping("/join-trip/{tripId}")
    void joinTrip(@PathVariable String tripId, @RequestHeader(value = "Authorization") String accessToken);

    @PostMapping("/leave-trip/{tripId}")
    void leaveTrip(@PathVariable String tripId, @RequestHeader(value = "Authorization") String accessToken);

    @PostMapping("/dissmiss/{tripId}")
    void cancelTrip(@PathVariable String tripId, @RequestBody CancelTripDto cancelTripDto, @RequestHeader(value = "Authorization") String accessToken);

    @PostMapping("/accept-user/{tripId}/{userId}")
    void acceptUserToTrip(@PathVariable String tripId, @PathVariable Long userId, @RequestHeader(value = "Authorization") String accessToken);

    @PostMapping("/deny-user/{tripId}/{userId}")
    void denyUserToTrip(@PathVariable String tripId, @PathVariable Long userId, @RequestHeader(value = "Authorization") String accessToken);

    @GetMapping("/view-trips")
    List<TripDto> viewTrips();

    @GetMapping("/view-trip/{tripId}")
    TripDetailsDto viewTrip(@PathVariable String tripId, @RequestHeader(value = "Authorization") String accessToken);
}
