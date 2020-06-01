package hr.fer.gateway.controllers;


import hr.fer.connector.dto.trips.CancelTripDto;
import hr.fer.connector.dto.trips.TripDetailsDto;
import hr.fer.connector.dto.trips.TripDto;
import hr.fer.connector.interfaces.TripsREST;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TripsGatewayController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TripsGatewayController.class);
    private final TripsREST tripsREST;

    @PostMapping("/create-trip")
    public String createTrip(@RequestBody TripDto tripDto, @RequestHeader(value = "Authorization") String accessToken) {
        LOGGER.info("createTrip" + tripDto + " " + accessToken);
        return tripsREST.createTrip(tripDto, accessToken);
    }

    @PostMapping("/edit-trip")
    public void editTrip(@RequestBody TripDto tripDto, @RequestHeader(value = "Authorization") String accessToken) {
        tripsREST.editTrip(tripDto, accessToken);
    }

    @PostMapping("/join-trip/{tripId}")
    public void joinTrip(@PathVariable String tripId, @RequestHeader(value = "Authorization") String accessToken) {
        tripsREST.joinTrip(tripId, accessToken);
    }

    @PostMapping("/leave-trip/{tripId}")
    public void leaveTrip(@PathVariable String tripId, @RequestHeader(value = "Authorization") String accessToken) {
        tripsREST.leaveTrip(tripId, accessToken);
    }

    @PostMapping("/cancel-trip/{tripId}")
    public void cancelTrip(@PathVariable String tripId, @RequestBody CancelTripDto cancelTripDto, @RequestHeader(value = "Authorization") String accessToken) {
        tripsREST.cancelTrip(tripId, cancelTripDto, accessToken);
    }

    @PostMapping("/accept-user/{tripId}/{userId}")
    public void acceptUserToTrip(@PathVariable String tripId, @PathVariable Long userId, @RequestHeader(value = "Authorization") String accessToken) {
        tripsREST.acceptUserToTrip(tripId, userId, accessToken);
    }

    @PostMapping("/deny-user/{tripId}/{userId}")
    public void denyUserToTrip(@PathVariable String tripId, @PathVariable Long userId, @RequestHeader(value = "Authorization") String accessToken) {
        tripsREST.denyUserToTrip(tripId, userId, accessToken);
    }

    @GetMapping("/view-trips")
    public List<TripDto> viewTrips() {
        return tripsREST.viewTrips();
    }

    @GetMapping("/view-trip/{tripId}")
    public TripDetailsDto viewTrips(@PathVariable String tripId, @RequestHeader(value = "Authorization") String accessToken) {
        return tripsREST.viewTrip(tripId, accessToken);
    }
}
