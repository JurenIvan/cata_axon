package hr.fer.connector.interfaces;

import hr.fer.connector.dto.auth.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("cata-auth")
public interface AuthREST {

    @PostMapping("/authenticate")
    AuthenticationResponseDto createAuthenticationToken(@RequestBody AuthenticationRequestDto authenticationRequest);

    @PostMapping("/register")
    void registerUser(@RequestBody RegisterRequestDto registerRequestDto);

    @PostMapping("/auth/jwt")
    PersonDto getUserForJWT(@RequestBody JWTokenDto jwTokenDto);
}
