package hr.fer.gateway.controllers;

import hr.fer.connector.interfaces.EmailREST;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmailGatewayController {

    private final EmailREST emailREST;
}
