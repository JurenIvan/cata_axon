package hr.fer.cata.auth.controllers;

import hr.fer.cata.auth.service.AuthService;
import hr.fer.cata.auth.service.PersonService;
import hr.fer.connector.dto.auth.*;
import hr.fer.connector.interfaces.AuthREST;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthREST {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    private final PersonService peopleService;
    private final AuthService authService;

    @Override
    @PostMapping("/authenticate")
    public AuthenticationResponseDto createAuthenticationToken(@RequestBody AuthenticationRequestDto authenticationRequest) {
        LOGGER.info("createAuthenticationToken " + authenticationRequest);
        return authService.createAuthenticationToken(authenticationRequest.getPassword(), authenticationRequest.getEmail());
    }

    @Override
    @PostMapping("/register")
    public void registerUser(@RequestBody RegisterRequestDto registerRequestDto) {
        LOGGER.info("registerUser " + registerRequestDto);
        peopleService.registerUser(registerRequestDto.getEmail(), registerRequestDto.getNickname(), registerRequestDto.getPassword());
    }

    @PostMapping("/auth/jwt")
    @Override
    public PersonDto getUserForJWT(@RequestBody JWTokenDto jwTokenDto) {
        LOGGER.info("getUserForJWT " + jwTokenDto);
        return peopleService.getUserForJWT(jwTokenDto.getJwt());
    }
}
