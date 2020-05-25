package hr.fer.connector.interfaces;

import hr.fer.connector.dto.auth.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("cata-auth")
public interface AuthREST {

    @PostMapping("/authenticate")
    public AuthenticationResponseDto createAuthenticationToken(@RequestBody AuthenticationRequestDto authenticationRequest);

    @PostMapping("/register")
    public void registerUser(@RequestBody RegisterRequestDto registerRequestDto);

    @PostMapping("/auth/jwt")
    public PersonDto getUserForJWT(@RequestBody JWTokenDto jwTokenDto);

}
