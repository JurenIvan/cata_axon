package hr.fer.cata.trips.controllers;

import hr.fer.cata.trips.service.TripsService;
import hr.fer.connector.dto.trips.CancelTripDto;
import hr.fer.connector.dto.trips.TripDetailsDto;
import hr.fer.connector.dto.trips.TripDto;
import hr.fer.connector.dto.trips.TripPreviewDto;
import hr.fer.connector.interfaces.TripsREST;
import hr.fer.connector.model.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class TripsController implements TripsREST {

    private final TripsService tripsService;

    @Override
    public String createTrip(@RequestBody TripDto tripDto) {
        return tripsService.createTrip(tripDto);
    }

    @Override
    public void editTrip(@RequestBody TripDto tripDto) {
        tripsService.editTrip(tripDto);
    }

    @Override
    public void joinTrip(String tripId, @RequestBody ContextHolder contextHolder) {
        tripsService.joinTrip(tripId, contextHolder);
    }

    @Override
    public void leaveTrip(String tripId, @RequestBody ContextHolder contextHolder) {
        tripsService.leaveTrip(tripId, contextHolder);
    }

    @Override
    public void cancelTrip(String tripId, CancelTripDto cancelTripDto) {
        tripsService.cancelTrip(tripId, cancelTripDto);
    }

    @Override
    public void acceptUserToTrip(String tripId, Long userId, @RequestBody ContextHolder contextHolder) {
        tripsService.acceptUserToTrip(tripId, userId, contextHolder);
    }

    @Override
    public void denyUserToTrip(String tripId, Long userId, @RequestBody ContextHolder contextHolder) {
        tripsService.denyUserToTrip(tripId, userId, contextHolder);
    }

    @Override
    public List<TripPreviewDto> viewTrips() {
        return tripsService.viewTrips().stream().map(e -> new TripPreviewDto(e.getTripId(), e.getTitle(), e.getDescription(), e.getPrice(), e.getDate(), e.getCancellationDate(), e.getPassengersCount())).collect(toList());
    }

    @Override
    public TripDetailsDto viewTrip(String tripId, @RequestBody ContextHolder contextHolder) {
        return tripsService.viewTrip(tripId, contextHolder);
    }
}
