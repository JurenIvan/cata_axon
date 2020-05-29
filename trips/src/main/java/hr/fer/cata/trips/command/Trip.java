package hr.fer.cata.trips.command;

import hr.fer.connector.api.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static java.time.LocalDateTime.now;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class Trip {

    private final static Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @AggregateIdentifier
    private String id;
    private String title;
    private String description;
    private double price;
    private LocalDateTime date;
    private LocalDateTime cancellationDate;
    private Set<Long> approvedUsersId;
    private Set<Long> pendingUsersId;

    public Trip() {
        log.debug("empty constructor invoked");
    }

    @CommandHandler
    public Trip(CreateTripCmd cmd) {
        log.debug("handling {}", cmd);

        if (cmd.getPrice() <= 0) throw new IllegalArgumentException("Price can not be negative");
        if (cmd.getCancelationDate().isAfter(cmd.getDate()))
            throw new IllegalArgumentException("Cancelation date cannot be after trip");
        if (cmd.getCancelationDate().isBefore(now())) throw new IllegalArgumentException("Cancelation date in past");
        if (cmd.getDate().isBefore(now())) throw new IllegalArgumentException("Trip before present");

        apply(new TripCreatedEvt(cmd.getId(), cmd.getTitle(), cmd.getDescription(), cmd.getPrice(), cmd.getDate(), cmd.getCancelationDate()));
    }

    @CommandHandler
    public void handle(UpdateTripCmd cmd) {
        log.debug("handling {}", cmd);

        if (cmd.getPrice() <= 0) throw new IllegalArgumentException("Price can not be negative");
        if (cmd.getCancelationDate().isAfter(cmd.getDate()))
            throw new IllegalArgumentException("Cancelation date cannot be after trip");
        if (cmd.getCancelationDate().isBefore(now())) throw new IllegalArgumentException("Cancelation date in past");
        if (cmd.getDate().isBefore(now())) throw new IllegalArgumentException("Trip before present");

        apply(new TripUpdatedEvt(cmd.getId(), cmd.getTitle(), cmd.getDescription(), cmd.getPrice(), cmd.getDate(), cmd.getCancelationDate()));
    }


    @CommandHandler
    public void handle(JoinTripCmd cmd) {
        log.debug("handling {}", cmd);

        if (approvedUsersId.contains(cmd.getUserId()))
            throw new IllegalStateException("User is already approved");
        if (pendingUsersId.contains(cmd.getUserId()))
            throw new IllegalStateException("User is already in pending list");
        if (date.isBefore(now()))
            throw new IllegalStateException("Cannot join on trip in past");

        apply(new JoinedTripEvt(cmd.getTripId(), cmd.getUserId()));
    }

    @CommandHandler
    public void handle(LeaveTripCmd cmd) {
        log.debug("handling {}", cmd);

        if (cancellationDate.isBefore(now()))
            throw new IllegalStateException("Cancelation date passed");
        if (!approvedUsersId.contains(cmd.getUserId()))
            throw new IllegalStateException("User is not assigned to this trip");

        apply(new UserLeftTripEvt(cmd.getTripId(), cmd.getUserId()));
    }

    @CommandHandler
    public void handle(LeavePendingTripCmd cmd) {
        log.debug("handling {}", cmd);

        if (cancellationDate.isBefore(now()))
            throw new IllegalStateException("Cancelation date passed");
        if (!pendingUsersId.contains(cmd.getUserId()))
            throw new IllegalStateException("User is not assigned to this trip");

        apply(new UserLeftPendingTripEvt(cmd.getTripId(), cmd.getUserId()));
    }

    @CommandHandler
    public void handle(CancelTripCmd cmd) {

        if (cancellationDate.isBefore(now()))
            throw new IllegalStateException("Cancelation date passed");

        apply(new TripCanceledEvt(cmd.getTripId(), cmd.getUserId(), approvedUsersId));
    }

    @CommandHandler
    public void handle(AcceptUserToTripCmd cmd) {
        log.debug("handling {}", cmd);

        if (date.isBefore(now()))
            throw new IllegalStateException("Trip in past");
        if (!pendingUsersId.contains(cmd.getUserId()))
            throw new IllegalStateException("User is not in pending list");

        apply(new UserAcceptedOnTripEvt(cmd.getTripId(), cmd.getUserId()));
    }

    @CommandHandler
    public void handle(DenyUserToTripCmd cmd) {
        log.debug("handling {}", cmd);

        if (!pendingUsersId.contains(cmd.getUserId()) || !approvedUsersId.contains(cmd.getUserId()))
            throw new IllegalStateException("User is not assigned to this trip");

        apply(new UserRefusedOnTripEvt(cmd.getTripId(), cmd.getUserId()));
    }

    @EventSourcingHandler
    public void on(TripCreatedEvt evt) {
        log.debug("applying {}", evt);
        id = evt.getId();
        title = evt.getTitle();
        description = evt.getDescription();
        price = evt.getPrice();
        date = evt.getDate();
        cancellationDate = evt.getCancelationDate();

        approvedUsersId = new HashSet<>();
        pendingUsersId = new HashSet<>();
    }

    @EventSourcingHandler
    public void on(UpdateTripCmd evt) {
        log.debug("applying {}", evt);
        id = evt.getId();
        title = evt.getTitle();
        description = evt.getDescription();
        price = evt.getPrice();
        date = evt.getDate();
        cancellationDate = evt.getCancelationDate();
    }

    @EventSourcingHandler
    public void handle(JoinedTripEvt cmd) {
        pendingUsersId.add(cmd.getUserId());
    }

    @EventSourcingHandler
    public void handle(UserLeftTripEvt cmd) {
        approvedUsersId.remove(cmd.getUserId());
    }

    @EventSourcingHandler
    public void handle(UserLeftPendingTripEvt cmd) {
        pendingUsersId.remove(cmd.getUserId());
    }

    @EventSourcingHandler
    public void handle(UserAcceptedOnTripEvt cmd) {
        pendingUsersId.remove(cmd.getUserId());
        approvedUsersId.add(cmd.getUserId());
    }

    @EventSourcingHandler
    public void handle(UserRefusedOnTripEvt cmd) {
        pendingUsersId.remove(cmd);
    }
}
