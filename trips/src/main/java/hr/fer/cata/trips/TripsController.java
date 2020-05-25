package hr.fer.cata.trips;

import hr.fer.connector.dto.auth.RegisterRequestDto;
import hr.fer.connector.interfaces.AuthREST;
import hr.fer.connector.interfaces.TripsREST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TripsController implements TripsREST {

    @Autowired
    private AuthREST authREST;

    @Override
    @RequestMapping("/trip/{applicationName}")
    public String example1(@PathVariable String applicationName) {
        authREST.registerUser(new RegisterRequestDto("asda.sad@asd.as", "asdasdasda", "sadasdasd"));
        System.out.println("example1 at TripsController");
        return "path returneed " + applicationName;
    }

}
