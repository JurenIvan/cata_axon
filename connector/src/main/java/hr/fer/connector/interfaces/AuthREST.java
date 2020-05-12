package hr.fer.connector.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("cata-auth")
public interface AuthREST {

    @PostMapping(value = "/authenticate")
    ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequestDto authenticationRequest);

    @GetMapping("/current-roles")
    List<Role> getCurrentRoles();

}
