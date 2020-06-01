package hr.fer.cata.reports.projection.passengersperyear;

import hr.fer.cata.reports.projection.utils.TripTime;
import hr.fer.cata.reports.projection.utils.TripsHelperRepository;
import hr.fer.connector.api.TripCreatedEvt;
import hr.fer.connector.api.UserAcceptedOnTripEvt;
import hr.fer.connector.api.UserLeftTripEvt;
import hr.fer.connector.dto.reports.YearPassengerCount;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PassengersPerYearProjection {

    private final PassengersPerYearRepository passengersPerYearRepository;
    private final TripsHelperRepository tripsRepository;

    @EventHandler
    private void on(TripCreatedEvt evt) {
        tripsRepository.save(new TripTime(evt.getId(), evt.getDate()));
    }

    @EventHandler
    private void on(UserAcceptedOnTripEvt evt) {
        var year = getYearFromEvent(evt.getTripId());
        var curr = passengersPerYearRepository.findById(year);

        if (curr.isEmpty())
            passengersPerYearRepository.save(new PassengerPerYear(year, 1));
        else
            passengersPerYearRepository.findById(year).map(e -> {
                e.setPassengerCount(e.getPassengerCount() + 1);
                return e;
            });
    }

    @EventHandler
    private void on(UserLeftTripEvt evt) {
        var year = getYearFromEvent(evt.getTripId());
        var curr = passengersPerYearRepository.findById(year);

        if (curr.isEmpty())
            passengersPerYearRepository.save(new PassengerPerYear(year, 1));
        else
            passengersPerYearRepository.findById(year).map(e -> {
                e.setPassengerCount(e.getPassengerCount() + 1);
                return e;
            });
    }


    private int getYearFromEvent(String tripId) {
        return tripsRepository.findById(tripId).orElseThrow(() -> new IllegalStateException("joined trip that doesnt exist")).getDate().getYear();
    }


    public List<YearPassengerCount> getNumberOfPassengersPerYear() {
        return passengersPerYearRepository.findAll().stream().map(e -> new YearPassengerCount(e.getYear(), e.getPassengerCount())).collect(Collectors.toList());
    }
}
