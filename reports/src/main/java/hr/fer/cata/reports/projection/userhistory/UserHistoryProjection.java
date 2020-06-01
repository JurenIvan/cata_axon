package hr.fer.cata.reports.projection.userhistory;

import hr.fer.connector.api.*;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserHistoryProjection {

    private final UserHistoryRepository userHistoryRepository;

    @EventHandler
    private void on(JoinedTripEvt evt) {
        var userHistory = userHistoryRepository.findById(evt.getUserId()).orElse(userHistoryRepository.save(new UserHistory(evt.getUserId())));
        userHistory.getPending().add(evt.getTripId());
    }

    @EventHandler
    private void on(UserLeftPendingTripEvt evt) {
        userHistoryRepository.findById(evt.getUserId()).map(e -> e.getPendinqQuit().add(evt.getTripId()));
    }

    @EventHandler
    private void on(UserLeftTripEvt evt) {
        userHistoryRepository.findById(evt.getUserId()).map(e -> e.getAcceptedQuit().add(evt.getTripId()));
    }

    @EventHandler
    private void on(UserAcceptedOnTripEvt evt) {
        userHistoryRepository.findById(evt.getUserId()).map(e -> e.getAccepted().add(evt.getTripId()));
    }

    @EventHandler
    private void on(UserRefusedOnTripEvt evt) {
        userHistoryRepository.findById(evt.getUserId()).map(e -> e.getRefused().add(evt.getTripId()));
    }

    @EventHandler
    private void on(TripCanceledEvt evt) {
        userHistoryRepository.findAll().stream()
                .map(e -> {
                    e.getPending().remove(evt.getTripId());
                    return e;
                })
                .map(e -> {
                    e.getAccepted().remove(evt.getTripId());
                    return e;
                })
                .map(e -> {
                    e.getPendinqQuit().remove(evt.getTripId());
                    return e;
                })
                .map(e -> {
                    e.getAcceptedQuit().remove(evt.getTripId());
                    return e;
                });
    }

    public UserHistory getHistoryForUser(Long userId) {
        return userHistoryRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("No such user"));
    }
}
