package hr.fer.gateway.controllers;

import hr.fer.connector.dto.auth.AuthenticationRequestDto;
import hr.fer.connector.dto.auth.AuthenticationResponseDto;
import hr.fer.connector.dto.auth.RegisterRequestDto;
import hr.fer.connector.interfaces.AuthREST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthGatewayController {

    @Autowired
    private AuthREST authREST;

    @PostMapping("/authenticate")
    public AuthenticationResponseDto createAuthenticationToken2(@RequestBody AuthenticationRequestDto authenticationRequest) {
        return authREST.createAuthenticationToken(authenticationRequest);
    }

    @PostMapping("/register")
    public void registerUser2(@RequestBody RegisterRequestDto registerRequestDto) {
        authREST.registerUser(registerRequestDto);
    }

}
