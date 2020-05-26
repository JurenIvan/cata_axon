package hr.fer.cata.trips.controllers;

import hr.fer.connector.dto.trips.TripDto;
import hr.fer.connector.dto.trips.UserTrip;
import hr.fer.connector.interfaces.TripsREST;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TripsController implements TripsREST {

    private static final Logger LOGGER = LoggerFactory.getLogger(TripsController.class);

    @Override
    @RequestMapping("/trip/{applicationName}")
    public String example1(String applicationName, @RequestHeader(value = "Authorization") String accessToken) {
        LOGGER.info("example1 at TripsController");
        return "path returneed " + applicationName;
    }

    @Override
    public void createTrip(TripDto tripDto, @RequestHeader(value = "Authorization") String accessToken) {
        LOGGER.info("createTrip at TripsController");
    }

    @Override
    public void joinTrip(UserTrip userTrip, @RequestHeader(value = "Authorization") String accessToken) {
        LOGGER.info("joinTrip at TripsController");
    }

    @Override
    public void leaveTrip(UserTrip userTrip, @RequestHeader(value = "Authorization") String accessToken) {
        LOGGER.info("leaveTrip at TripsController");
    }

    @Override
    public void approveTrip(UserTrip userTrip, @RequestHeader(value = "Authorization") String accessToken) {
        LOGGER.info("approveTrip at TripsController");
    }

    @Override
    public void dismissTrip(UserTrip userTrip, @RequestHeader(value = "Authorization") String accessToken) {
        LOGGER.info("dismissTrip at TripsController");
    }

    @Override
    public void acceptUserToTrip(UserTrip userTrip, @RequestHeader(value = "Authorization") String accessToken) {
        LOGGER.info("acceptUserToTrip at TripsController");
    }

    @Override
    public void denyUserToTrip(UserTrip userTrip, @RequestHeader(value = "Authorization") String accessToken) {
        LOGGER.info("denyUserToTrip at TripsController");
    }

    @Override
    public List<TripDto> viewTrips() {
        LOGGER.info("viewTrips at TripsController");
        return List.of();
    }

}
