package hr.fer.cata.trips.projections.availabletrips;

import hr.fer.connector.api.*;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TripOverviewProjection {

    private final TripOverviewRepository tripOverviewRepository;

    @EventHandler
    public void on(TripCreatedEvt event) {
        tripOverviewRepository.save(new TripOverview(event.getId(), event.getTitle(), event.getDescription(), event.getPrice(), event.getDate(), event.getCancelationDate(), 0));
    }

    @EventHandler
    public void on(TripUpdatedEvt event) {
        tripOverviewRepository.save(
                tripOverviewRepository.findById(event.getId()).map(tripDetails -> {
                    tripDetails.setDate(event.getDate());
                    tripDetails.setPrice(event.getPrice());
                    tripDetails.setCancellationDate(event.getCancelationDate());
                    tripDetails.setDescription(event.getDescription());
                    tripDetails.setTitle(event.getTitle());
                    return tripDetails;
                }).get());
    }

    @EventHandler
    public void on(UserAcceptedOnTripEvt event) {
        tripOverviewRepository.save(
                tripOverviewRepository.findById(event.getTripId()).map(tripDetails -> {
                    tripDetails.setPassengersCount(tripDetails.getPassengersCount() + 1);
                    return tripDetails;
                }).get());
    }

    @EventHandler
    public void on(UserLeftTripEvt event) {
        tripOverviewRepository.save(
                tripOverviewRepository.findById(event.getTripId()).map(tripDetails -> {
                    tripDetails.setPassengersCount(tripDetails.getPassengersCount() - 1);
                    return tripDetails;
                }).get());
    }

    @EventHandler
    public void on(TripCanceledEvt event) {
        tripOverviewRepository.delete(tripOverviewRepository.findById(event.getTripId()).orElseThrow(() -> new IllegalStateException("No such trip existed.")));
    }

    public List<TripOverview> findAll() {
        return tripOverviewRepository.findAll();
    }

    public TripOverview findOne(String tripId) {
        return tripOverviewRepository.findById(tripId).orElse(null);
    }
}
