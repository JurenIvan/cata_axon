package hr.fer.cata.auth.controllers;

import hr.fer.cata.auth.service.PersonService;
import hr.fer.connector.dto.auth.JWTokenDto;
import hr.fer.connector.dto.auth.PersonDto;
import hr.fer.connector.interfaces.InternalAuthREST;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class InternalController implements InternalAuthREST {

    private final PersonService personService;


    @PostMapping("/auth/jwt")
    @Override
    public PersonDto getUserForJWT(@RequestBody JWTokenDto jwTokenDto) {
        return personService.getUserForJWT(jwTokenDto.getJwt());
    }

}
