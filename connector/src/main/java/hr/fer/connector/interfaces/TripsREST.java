package hr.fer.connector.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("cata-trips")
public interface TripsREST {

    @PostMapping("/create-trip")
    void createTrip(@RequestBody TripDto tripDto);

    @PostMapping("/join-trip")
    void joinTrip(@RequestBody UserTrip userTrip);

    @PostMapping("/leave-trip")
    void leaveTrip(@RequestBody UserTrip userTrip);

    @PostMapping("/approve")
    void approveTrip(@RequestBody UserTrip userTrip);

    @PostMapping("/dissmiss")
    void dismissTrip(@RequestBody UserTrip userTrip);

    @PostMapping("/accept-user")
    void acceptUserToTrip(UserTrip userTrip);

    @PostMapping("/deny-user")
    void denyUserToTrip(UserTrip userTrip);

    @GetMapping("/view-trips")
    List<TripDto> viewTrips();
}
