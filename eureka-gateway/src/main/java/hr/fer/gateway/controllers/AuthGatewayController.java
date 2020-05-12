package hr.fer.gateway.controllers;

import hr.fer.connector.interfaces.AuthREST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthGatewayController {

    @Autowired
    private AuthREST authREST;
}
