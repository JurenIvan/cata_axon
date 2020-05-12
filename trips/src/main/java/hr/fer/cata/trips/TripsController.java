package hr.fer.cata.trips;

import hr.fer.connector.interfaces.TripsREST;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TripsController implements TripsREST {

    @Override
    @RequestMapping("/trip/{applicationName}")
    public String example1(@PathVariable String applicationName) {
        System.out.println("example1 at TripsController");
        return "path returneed " + applicationName;
    }

}
