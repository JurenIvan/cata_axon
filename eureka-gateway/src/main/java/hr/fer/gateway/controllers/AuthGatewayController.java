package hr.fer.gateway.controllers;

import hr.fer.connector.dto.auth.AuthenticationRequestDto;
import hr.fer.connector.dto.auth.AuthenticationResponseDto;
import hr.fer.connector.dto.auth.RegisterRequestDto;
import hr.fer.connector.interfaces.AuthREST;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthGatewayController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthGatewayController.class);
    private final AuthREST authREST;

    @PostMapping("/authenticate")
    public AuthenticationResponseDto createAuthenticationToken(@RequestBody AuthenticationRequestDto authenticationRequest) {
        LOGGER.info("createAuthenticationToken" + authenticationRequest);
        return authREST.createAuthenticationToken(authenticationRequest);
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody RegisterRequestDto registerRequestDto) {
        LOGGER.info("registerUser " + registerRequestDto);
        authREST.registerUser(registerRequestDto);
    }

}
