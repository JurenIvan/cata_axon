package hr.fer.cata.trips.service;

import hr.fer.cata.trips.projections.availabletrips.TripOverview;
import hr.fer.cata.trips.projections.availabletrips.TripOverviewProjection;
import hr.fer.cata.trips.projections.details.TripDetailsProjection;
import hr.fer.connector.api.*;
import hr.fer.connector.dto.trips.CancelTripDto;
import hr.fer.connector.dto.trips.TripDetailsDto;
import hr.fer.connector.dto.trips.TripDto;
import hr.fer.connector.model.ContextHolder;
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

    private final CommandGateway commandGateway;
    private final TripOverviewProjection tripOverviewProjection;
    private final TripDetailsProjection tripDetailsProjection;

    public void acceptUserToTrip(String tripId, Long userId, ContextHolder contextHolder) {
        if (!contextHolder.getRole().equals(ADMIN))
            throw new IllegalStateException("Only admins can accept user to trip");

        commandGateway.send(new AcceptUserToTripCmd(tripId, userId));
    }

    public String createTrip(TripDto tripDto) {
        if (!tripDto.getRole().equals(ADMIN))
            throw new IllegalStateException("Only admins can create events");

        String id = UUID.randomUUID().toString().substring(0, 11).toUpperCase();
        commandGateway.send(new CreateTripCmd(id, tripDto.getTitle(), tripDto.getDescription(), tripDto.getPrice(), tripDto.getDate(), tripDto.getCancellationDate()));
        return id;
    }

    public void editTrip(TripDto tripDto) {
        if (!tripDto.getRole().equals(ADMIN))
            throw new IllegalStateException("Only admins can update events");

        commandGateway.send(new UpdateTripCmd(tripDto.getTripId(), tripDto.getTitle(), tripDto.getDescription(), tripDto.getPrice(), tripDto.getDate(), tripDto.getCancellationDate()));
    }

    public void cancelTrip(String tripId, CancelTripDto cancelTripDto) {
        if (!cancelTripDto.getRole().equals(ADMIN))
            throw new IllegalStateException("Only guests can join trips");

        commandGateway.send(new CancelTripCmd(tripId, cancelTripDto.getId(), cancelTripDto.getExplanation()));
    }

    public void joinTrip(String tripId, ContextHolder contextHolder) {
        if (!contextHolder.getRole().equals(GUEST))
            throw new IllegalStateException("Only guests can join trips");

        commandGateway.send(new JoinTripCmd(tripId, contextHolder.getId()));
    }

    public void leaveTrip(String tripId, ContextHolder contextHolder) {
        if (!contextHolder.getRole().equals(GUEST))
            throw new IllegalStateException("Only guests can leave trips");

        commandGateway.send(new LeaveTripCmd(tripId, contextHolder.getId()));
    }

    public void denyUserToTrip(String tripId, Long userId, ContextHolder contextHolder) {
        if (!contextHolder.getRole().equals(ADMIN))
            throw new IllegalStateException("Only admins can deny user from trip");

        commandGateway.send(new DenyUserToTripCmd(tripId, userId));
    }

    public List<TripOverview> viewTrips() {
        return tripOverviewProjection.findAll();
    }

    public TripDetailsDto viewTrip(String tripId, ContextHolder contextHolder) {

        if (!contextHolder.getRole().equals(ADMIN))
            throw new IllegalStateException("Only admins are authorized to see this data");
        return tripDetailsProjection.findOne(tripId);
    }
}
