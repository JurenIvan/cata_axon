package hr.fer.cata.trips.projections.details;

import hr.fer.cata.trips.api.*;
import hr.fer.cata.trips.projections.availabletrips.TripOverview;
import hr.fer.cata.trips.projections.availabletrips.TripOverviewProjection;
import hr.fer.connector.dto.trips.TripDetailsDto;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TripDetailsProjection {

    private final TripDetailsRepository tripDetailsRepository;
    private final TripOverviewProjection tripOverviewProjection;

    private static TripDetailsDto from(TripOverview tripOverview, Optional<TripDetails> details2) {
        TripDetails details = details2.orElseGet(() -> new TripDetails(tripOverview.getTripId()));
        return new TripDetailsDto(details.getTripId(), tripOverview.getTitle(), tripOverview.getDescription(), tripOverview.getPrice(), tripOverview.getDate(), tripOverview.getCancellationDate(), details.getPendingUsersIds(), details.getQuittersWhilePendingUsersIds(), details.getApprovedUsersIds(), details.getRefusedUsersIds(), details.getQuittersUsersIds());
    }

    @EventHandler
    public void on(TripCreatedEvt event) {
        tripDetailsRepository.save(new TripDetails(event.getId()));
    }

    @EventHandler
    public void on(TripCanceledEvt event) {
        tripDetailsRepository.delete(tripDetailsRepository.findById(event.getTripId()).orElseThrow(() -> new IllegalStateException("No such trip existed.")));
    }

    @EventHandler
    public void on(JoinedTripEvt event) {
        tripDetailsRepository.findById(event.getTripId()).map(tripDetails -> {
            tripDetails.getPendingUsersIds().add(event.getUserId());
            return tripDetails;
        }).get();
    }

    @EventHandler
    public void on(UserLeftPendingTripEvt event) {
        tripDetailsRepository.findById(event.getTripId()).map(tripDetails -> {
            tripDetails.getQuittersWhilePendingUsersIds().add(event.getUserId());
            return tripDetails;
        }).get();
    }

    @EventHandler
    public void on(UserLeftTripEvt event) {
        tripDetailsRepository.findById(event.getTripId()).map(tripDetails -> {
            tripDetails.getQuittersUsersIds().add(event.getUserId());
            return tripDetails;
        }).get();
    }

    @EventHandler
    public void on(UserAcceptedOnTripEvt event) {
        tripDetailsRepository.findById(event.getTripId()).map(tripDetails -> {
            tripDetails.getApprovedUsersIds().add(event.getUserId());
            return tripDetails;
        }).get();
    }

    @EventHandler
    public void on(UserRefusedOnTripEvt event) {
        tripDetailsRepository.findById(event.getTripId()).map(tripDetails -> {
            tripDetails.getRefusedUsersIds().add(event.getUserId());
            return tripDetails;
        }).get();
    }

    public List<TripDetailsDto> findAll() {
        return tripOverviewProjection.findAll().stream().map(e -> from(e, tripDetailsRepository.findById(e.getTripId()))).collect(Collectors.toList());
    }

    public TripDetailsDto findOne(String tripId) {
        return from(tripOverviewProjection.findOne(tripId), tripDetailsRepository.findById(tripId));
    }
}
