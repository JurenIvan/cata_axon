package hr.fer.gateway.controllers;

import hr.fer.connector.interfaces.EmailREST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailGatewayController {

    @Autowired
    private EmailREST emailREST;
}
