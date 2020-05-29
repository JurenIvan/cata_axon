package hr.fer.cata.trips.controllers;

import hr.fer.cata.trips.service.TripsService;
import hr.fer.connector.dto.trips.TripDetailsDto;
import hr.fer.connector.dto.trips.TripDto;
import hr.fer.connector.interfaces.TripsREST;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class TripsController implements TripsREST {

    private final TripsService tripsService;

    @Override
    public String createTrip(TripDto tripDto, @RequestHeader(value = "Authorization") String accessToken) {
        return tripsService.createTrip(tripDto);
    }

    @Override
    public void editTrip(TripDto tripDto, String accessToken) {
        tripsService.editTrip(tripDto);
    }

    @Override
    public void joinTrip(String tripId, String accessToken) {
        tripsService.joinTrip(tripId);
    }

    @Override
    public void leaveTrip(String tripId, String accessToken) {
        tripsService.leaveTrip(tripId);
    }

    @Override
    public void dismissTrip(String tripId, String accessToken) {
        tripsService.dismissTrip(tripId);
    }

    @Override
    public void acceptUserToTrip(String tripId, Long userId, String accessToken) {
        tripsService.acceptUserToTrip(tripId, userId);
    }

    @Override
    public void denyUserToTrip(String tripId, Long userId, String accessToken) {
        tripsService.denyUserToTrip(tripId, userId);
    }

    @Override
    public List<TripDto> viewTrips() {
        return tripsService.viewTrips().stream().map(e -> new TripDto(e.getTripId(), e.getTitle(), e.getDescription(), e.getPrice(), e.getDate(), e.getCancellationDate(), e.getPassengersCount())).collect(toList());
    }

    @Override
    public TripDetailsDto viewTrip(String tripId, String accessToken) {
        return tripsService.viewTrip(tripId);
    }
}
