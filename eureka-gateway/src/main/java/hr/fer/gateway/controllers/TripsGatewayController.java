package hr.fer.gateway.controllers;


import hr.fer.connector.dto.trips.CancelTripDto;
import hr.fer.connector.dto.trips.TripDetailsDto;
import hr.fer.connector.dto.trips.TripDto;
import hr.fer.connector.dto.trips.TripPreviewDto;
import hr.fer.connector.interfaces.TripsREST;
import hr.fer.gateway.services.ContextService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TripsGatewayController {

    private final TripsREST tripsREST;
    private final ContextService contextService;

    @PostMapping("/create-trip")
    public String createTrip(@RequestBody TripDto tripDto) {
        tripDto.setAuthorizationPart(contextService.getLoggedIn());
        return tripsREST.createTrip(tripDto);
    }

    @PostMapping("/edit-trip")
    public void editTrip(@RequestBody TripDto tripDto) {
        tripDto.setAuthorizationPart(contextService.getLoggedIn());
        tripsREST.editTrip(tripDto);
    }

    @PostMapping("/join-trip/{tripId}")
    public void joinTrip(@PathVariable String tripId) {
        tripsREST.joinTrip(tripId, contextService.getLoggedIn());
    }

    @PostMapping("/leave-trip/{tripId}")
    public void leaveTrip(@PathVariable String tripId) {
        tripsREST.leaveTrip(tripId, contextService.getLoggedIn());
    }

    @PostMapping("/cancel-trip/{tripId}")
    public void cancelTrip(@PathVariable String tripId, @RequestBody CancelTripDto cancelTripDto) {
        cancelTripDto.setAuthorizationPart(contextService.getLoggedIn());
        tripsREST.cancelTrip(tripId, cancelTripDto);
    }

    @PostMapping("/accept-user/{tripId}/{userId}")
    public void acceptUserToTrip(@PathVariable String tripId, @PathVariable Long userId) {
        tripsREST.acceptUserToTrip(tripId, userId, contextService.getLoggedIn());
    }

    @PostMapping("/deny-user/{tripId}/{userId}")
    public void denyUserToTrip(@PathVariable String tripId, @PathVariable Long userId) {
        tripsREST.denyUserToTrip(tripId, userId, contextService.getLoggedIn());
    }

    @GetMapping("/view-trips")
    public List<TripPreviewDto> viewTrips() {
        return tripsREST.viewTrips();
    }

    @GetMapping("/view-trip/{tripId}")
    public TripDetailsDto viewTrips(@PathVariable String tripId) {
        return tripsREST.viewTrip(tripId, contextService.getLoggedIn());
    }
}
