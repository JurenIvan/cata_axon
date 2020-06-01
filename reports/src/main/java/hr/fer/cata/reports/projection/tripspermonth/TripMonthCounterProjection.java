package hr.fer.cata.reports.projection.tripspermonth;

import hr.fer.cata.reports.projection.utils.TripTime;
import hr.fer.cata.reports.projection.utils.TripsHelperRepository;
import hr.fer.connector.api.TripCanceledEvt;
import hr.fer.connector.api.TripCreatedEvt;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TripMonthCounterProjection {

    private final TripMonthCounterRepository repository;
    private final TripsHelperRepository helperRepository;

    @EventHandler
    private void on(TripCreatedEvt evt) {
        var tripDate = evt.getDate();
        var year = tripDate.getYear();
        var month = tripDate.getMonth().getValue();

        var tripMonth = repository.findById(year).orElse(new TripMonthCounter(year));
        var list = tripMonth.getPassengerCountPerMonth();
        list.set(month - 1, list.get(month - 1) + 1);
        repository.save(tripMonth);
        helperRepository.save(new TripTime(evt.getId(), evt.getDate()));
    }

    @EventHandler
    private void on(TripCanceledEvt evt) {
        var tripDetails = helperRepository.findById(evt.getTripId()).orElseThrow(() -> new IllegalStateException("cannot cancel trip that hasnt started"));
        var tripDate = tripDetails.getDate();
        var year = tripDate.getYear();
        var month = tripDate.getMonth().getValue();

        var tripMonth = repository.findById(year).orElse(new TripMonthCounter(year));
        var list = tripMonth.getPassengerCountPerMonth();
        list.set(month - 1, list.get(month - 1) - 1);
        repository.save(tripMonth);
    }

    public TripMonthCounter find(int year) {
        return repository.findById(year).orElse(new TripMonthCounter(year));
    }

}
