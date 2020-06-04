package hr.fer.gateway.controllers;

import hr.fer.connector.dto.auth.AuthenticationRequestDto;
import hr.fer.connector.dto.auth.AuthenticationResponseDto;
import hr.fer.connector.dto.auth.RegisterRequestDto;
import hr.fer.connector.interfaces.AuthREST;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthGatewayController {

    private final AuthREST authREST;

    @PostMapping("/authenticate")
    public AuthenticationResponseDto createAuthenticationToken(@RequestBody AuthenticationRequestDto authenticationRequest) {
        return authREST.createAuthenticationToken(authenticationRequest);
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody RegisterRequestDto registerRequestDto) {
        authREST.registerUser(registerRequestDto);
    }
}
