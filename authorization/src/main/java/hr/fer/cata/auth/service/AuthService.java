package hr.fer.cata.auth.service;

import hr.fer.cata.auth.model.Person;
import hr.fer.connector.dto.auth.AuthenticationResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final PersonService peopleService;
    private final JwtUtil jwtUtil;

    public AuthenticationResponseDto createAuthenticationToken(String password, String email) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (BadCredentialsException e) {
            throw new IllegalArgumentException("Wrong combination of email and password");
        }

        final Person person = peopleService.loadUserByUsername(email);
        final String jwt = jwtUtil.generateToken(person);
        return new AuthenticationResponseDto(jwt, person.getId(), person.getRole());
    }
}
