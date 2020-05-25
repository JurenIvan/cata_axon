package hr.fer.connector.interfaces;

import hr.fer.connector.dto.auth.AuthenticationRequestDto;
import hr.fer.connector.dto.auth.AuthenticationResponseDto;
import hr.fer.connector.dto.auth.RegisterRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("cata-auth")
public interface AuthREST {

    @PostMapping("/authenticate")
    public AuthenticationResponseDto createAuthenticationToken(@RequestBody AuthenticationRequestDto authenticationRequest);

    @PostMapping("/register")
    public void registerUser(@RequestBody RegisterRequestDto registerRequestDto);

}
