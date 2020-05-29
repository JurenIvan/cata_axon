package hr.fer.cata.trips.service;

import hr.fer.cata.trips.projections.availabletrips.TripOverview;
import hr.fer.cata.trips.projections.availabletrips.TripOverviewProjection;
import hr.fer.cata.trips.projections.details.TripDetailsProjection;
import hr.fer.connector.api.*;
import hr.fer.connector.dto.trips.TripDetailsDto;
import hr.fer.connector.dto.trips.TripDto;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static hr.fer.connector.model.Role.ADMIN;
import static hr.fer.connector.model.Role.GUEST;

@Service
@RequiredArgsConstructor
public class TripsService {

    private final ContextService contextService;
    private final CommandGateway commandGateway;
    private final TripOverviewProjection tripOverviewProjection;
    private final TripDetailsProjection tripDetailsProjection;

    public String createTrip(TripDto tripDto) {
        if (!contextService.getLoggedIn().getRole().equals(ADMIN))
            throw new IllegalStateException("Only admins can create events");

        String id = UUID.randomUUID().toString().substring(0, 11).toUpperCase();
        commandGateway.send(new CreateTripCmd(id, tripDto.getTitle(), tripDto.getDescription(), tripDto.getPrice(), tripDto.getDate(), tripDto.getCancellationDate()));
        return id;
    }

    public void editTrip(TripDto tripDto) {
        if (!contextService.getLoggedIn().getRole().equals(ADMIN))
            throw new IllegalStateException("Only admins can update events");

        commandGateway.send(new UpdateTripCmd(tripDto.getTripId(), tripDto.getTitle(), tripDto.getDescription(), tripDto.getPrice(), tripDto.getDate(), tripDto.getCancellationDate()));
        //       todo notify users
    }

    public void dismissTrip(String tripId) {
        if (!contextService.getLoggedIn().getRole().equals(ADMIN))
            throw new IllegalStateException("Only guests can join trips");

        commandGateway.send(new CancelTripCmd(tripId, contextService.getLoggedIn().getId()));
    }

    public void joinTrip(String tripId) {
        if (!contextService.getLoggedIn().getRole().equals(GUEST))
            throw new IllegalStateException("Only guests can join trips");

        commandGateway.send(new JoinTripCmd(tripId, contextService.getLoggedIn().getId()));
    }

    public void leaveTrip(String tripId) {
        if (!contextService.getLoggedIn().getRole().equals(GUEST))
            throw new IllegalStateException("Only guests can leave trips");

        commandGateway.send(new LeaveTripCmd(tripId, contextService.getLoggedIn().getId()));
    }

    public void acceptUserToTrip(String tripId, Long userId) {
        if (!contextService.getLoggedIn().getRole().equals(ADMIN))
            throw new IllegalStateException("Only admins can accept sser to trip");

        commandGateway.send(new AcceptUserToTripCmd(tripId, userId));
    }

    public void denyUserToTrip(String tripId, Long userId) {
        if (!contextService.getLoggedIn().getRole().equals(ADMIN))
            throw new IllegalStateException("Only admins can deny user from trip");

        commandGateway.send(new DenyUserToTripCmd(tripId, userId));
    }

    public List<TripOverview> viewTrips() {
        return tripOverviewProjection.findAll();
    }

    public TripDetailsDto viewTrip(String tripId) {

        if (!contextService.getLoggedIn().getRole().equals(ADMIN))
            throw new IllegalStateException("Only admins are authorized to see this data");
        return tripDetailsProjection.findOne(tripId);
    }
}
