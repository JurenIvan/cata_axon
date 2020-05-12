package hr.fer.gateway.controllers;

import hr.fer.connector.interfaces.ReportsREST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportsGatewayController {

    @Autowired
    private ReportsREST reportsREST;
}
