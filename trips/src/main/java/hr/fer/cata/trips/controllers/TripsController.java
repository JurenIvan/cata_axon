package hr.fer.cata.trips.controllers;

import hr.fer.connector.dto.trips.TripDto;
import hr.fer.connector.dto.trips.UserTrip;
import hr.fer.connector.interfaces.TripsREST;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TripsController implements TripsREST {

    @Override
    @RequestMapping("/trip/{applicationName}")
    public String example1(String applicationName, @RequestHeader(value = "Authorization") String accessToken) {
        System.out.println("example1 at TripsController");
        return "path returneed " + applicationName;
    }

    @Override
    public void createTrip(TripDto tripDto, @RequestHeader(value = "Authorization") String accessToken) {
        System.out.println("createTrip at TripsController");
    }

    @Override
    public void joinTrip(UserTrip userTrip, @RequestHeader(value = "Authorization") String accessToken) {
        System.out.println("joinTrip at TripsController");
    }

    @Override
    public void leaveTrip(UserTrip userTrip, @RequestHeader(value = "Authorization") String accessToken) {
        System.out.println("leaveTrip at TripsController");
    }

    @Override
    public void approveTrip(UserTrip userTrip, @RequestHeader(value = "Authorization") String accessToken) {
        System.out.println("approveTrip at TripsController");
    }

    @Override
    public void dismissTrip(UserTrip userTrip, @RequestHeader(value = "Authorization") String accessToken) {
        System.out.println("dismissTrip at TripsController");
    }

    @Override
    public void acceptUserToTrip(UserTrip userTrip, @RequestHeader(value = "Authorization") String accessToken) {
        System.out.println("acceptUserToTrip at TripsController");
    }

    @Override
    public void denyUserToTrip(UserTrip userTrip, @RequestHeader(value = "Authorization") String accessToken) {
        System.out.println("denyUserToTrip at TripsController");
    }

    @Override
    public List<TripDto> viewTrips() {
        return List.of();
    }

}
