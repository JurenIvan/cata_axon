package hr.fer.connector.interfaces;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("cata-auth")
public interface AuthREST {

//    @PostMapping(value = "/authenticate")
//    ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequestDto authenticationRequest);
//
//    @GetMapping("/current-roles")
//    List<Role> getCurrentRoles();

}
