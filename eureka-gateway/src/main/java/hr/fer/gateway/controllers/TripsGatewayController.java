package hr.fer.gateway.controllers;


import hr.fer.connector.dto.trips.TripDto;
import hr.fer.connector.dto.trips.UserTrip;
import hr.fer.connector.interfaces.TripsREST;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TripsGatewayController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TripsGatewayController.class);
    private final TripsREST tripsREST;

    @RequestMapping("/trip/{applicationName}")
    public String example1(@PathVariable String applicationName, @RequestHeader("Authorization") String accessToken) {
        LOGGER.info("example1" + applicationName + " " + accessToken);
        return tripsREST.example1(applicationName, accessToken);
    }

    @PostMapping("/create-trip")
    public void createTrip(@RequestBody TripDto tripDto, @RequestHeader(value = "Authorization") String accessToken) {
        LOGGER.info("createTrip" + tripDto + " " + accessToken);
        tripsREST.createTrip(tripDto, accessToken);
    }

    @PostMapping("/join-trip")
    public void joinTrip(@RequestBody UserTrip userTrip, @RequestHeader(value = "Authorization") String accessToken) {
        LOGGER.info("joinTrip" + userTrip + " " + accessToken);
        tripsREST.joinTrip(userTrip, accessToken);
    }

    @PostMapping("/leave-trip")
    public void leaveTrip(@RequestBody UserTrip userTrip, @RequestHeader(value = "Authorization") String accessToken) {
        LOGGER.info("leaveTrip" + userTrip + " " + accessToken);
        tripsREST.leaveTrip(userTrip, accessToken);
    }

    @PostMapping("/approve")
    public void approveTrip(@RequestBody UserTrip userTrip, @RequestHeader(value = "Authorization") String accessToken) {
        LOGGER.info("approveTrip " + userTrip + " " + accessToken);
        tripsREST.approveTrip(userTrip, accessToken);
    }

    @PostMapping("/dissmiss")
    public void dismissTrip(@RequestBody UserTrip userTrip, @RequestHeader(value = "Authorization") String accessToken) {
        LOGGER.info("dismissTrip " + userTrip + " " + accessToken);
        tripsREST.dismissTrip(userTrip, accessToken);
    }

    @PostMapping("/accept-user")
    public void acceptUserToTrip(UserTrip userTrip, @RequestHeader(value = "Authorization") String accessToken) {
        LOGGER.info("acceptUserToTrip " + userTrip + " " + accessToken);
        tripsREST.acceptUserToTrip(userTrip, accessToken);
    }

    @PostMapping("/deny-user")
    public void denyUserToTrip(UserTrip userTrip, @RequestHeader(value = "Authorization") String accessToken) {
        LOGGER.info("denyUserToTrip " + userTrip + " " + accessToken);
        tripsREST.denyUserToTrip(userTrip, accessToken);
    }

    @GetMapping("/view-trips")
    public List<TripDto> viewTrips() {
        LOGGER.info("viewTrips");
        return tripsREST.viewTrips();
    }
}
