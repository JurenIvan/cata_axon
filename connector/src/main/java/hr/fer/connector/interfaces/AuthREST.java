package hr.fer.connector.interfaces;

import hr.fer.connector.dto.auth.AuthenticationRequestDto;
import hr.fer.connector.dto.auth.AuthenticationResponseDto;
import hr.fer.connector.dto.auth.JWTokenDto;
import hr.fer.connector.dto.auth.RegisterRequestDto;
import hr.fer.connector.model.ContextHolder;
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
    ContextHolder getUserForJWT(@RequestBody JWTokenDto jwTokenDto);
}
