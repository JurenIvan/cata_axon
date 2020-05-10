package hr.fer.gateway.controllers;

import hr.fer.gateway.interfaces.TripsREST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TripsGatewayController {

    @Autowired
    private TripsREST tripsREST;

    @RequestMapping("/trip/{applicationName}")
    public String example1(@PathVariable String applicationName) {
        System.out.println("example1 in Gateway");
        return tripsREST.example1(applicationName);
    }

}
