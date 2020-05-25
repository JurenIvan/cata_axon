package hr.fer.cata.auth.controllers;

import hr.fer.cata.auth.model.Person;
import hr.fer.cata.auth.service.JwtUtil;
import hr.fer.cata.auth.service.PersonService;
import hr.fer.connector.dto.auth.AuthenticationRequestDto;
import hr.fer.connector.dto.auth.AuthenticationResponseDto;
import hr.fer.connector.dto.auth.RegisterRequestDto;
import hr.fer.connector.interfaces.AuthREST;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthREST {

    private final AuthenticationManager authenticationManager;
    private final PersonService peopleService;
    private final JwtUtil jwtUtil;

    @Override
    @PostMapping("/authenticate")
    public AuthenticationResponseDto createAuthenticationToken(@RequestBody AuthenticationRequestDto authenticationRequest) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new IllegalArgumentException("Wrong combination of email and password");
        }

        final Person person = peopleService.loadUserByUsername(authenticationRequest.getEmail());
        final String jwt = jwtUtil.generateToken(person);
        return new AuthenticationResponseDto(jwt, person.getId(), person.getRole());
    }

    @Override
    @PostMapping("/register")
    public void registerUser(@RequestBody RegisterRequestDto registerRequestDto) {
        peopleService.registerUser(registerRequestDto.getEmail(), registerRequestDto.getNickname(), registerRequestDto.getPassword());
    }
}
