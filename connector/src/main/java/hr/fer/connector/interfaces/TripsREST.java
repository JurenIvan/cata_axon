package hr.fer.connector.interfaces;

import hr.fer.connector.dto.trips.TripDto;
import hr.fer.connector.dto.trips.UserTrip;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("cata-trips")
public interface TripsREST {

    @RequestMapping("/trip/{applicationName}")
    String example1(@PathVariable String applicationName, @RequestHeader(value = "Authorization") String accessToken);

    @PostMapping("/create-trip")
    void createTrip(@RequestBody TripDto tripDto, @RequestHeader(value = "Authorization") String accessToken);

    @PostMapping("/join-trip")
    void joinTrip(@RequestBody UserTrip userTrip, @RequestHeader(value = "Authorization") String accessToken);

    @PostMapping("/leave-trip")
    void leaveTrip(@RequestBody UserTrip userTrip, @RequestHeader(value = "Authorization") String accessToken);

    @PostMapping("/approve")
    void approveTrip(@RequestBody UserTrip userTrip, @RequestHeader(value = "Authorization") String accessToken);

    @PostMapping("/dissmiss")
    void dismissTrip(@RequestBody UserTrip userTrip, @RequestHeader(value = "Authorization") String accessToken);

    @PostMapping("/accept-user")
    void acceptUserToTrip(UserTrip userTrip, @RequestHeader(value = "Authorization") String accessToken);

    @PostMapping("/deny-user")
    void denyUserToTrip(UserTrip userTrip, @RequestHeader(value = "Authorization") String accessToken);

    @GetMapping("/view-trips")
    List<TripDto> viewTrips();
}
