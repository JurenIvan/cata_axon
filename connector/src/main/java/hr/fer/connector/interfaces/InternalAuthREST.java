package hr.fer.connector.interfaces;

import hr.fer.connector.dto.auth.JWTokenDto;
import hr.fer.connector.dto.auth.PersonDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@FeignClient("cata-auth")
public interface InternalAuthREST {

    @PostMapping("/auth/jwt")
    public PersonDto getUserForJWT(@RequestBody JWTokenDto jwTokenDto);
}
