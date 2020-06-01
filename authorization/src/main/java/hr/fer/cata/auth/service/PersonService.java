package hr.fer.cata.auth.service;

import hr.fer.cata.auth.model.Person;
import hr.fer.cata.auth.repository.PersonRepository;
import hr.fer.connector.model.ContextHolder;
import hr.fer.connector.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PersonService implements UserDetailsService {

    private final JwtUtil jwtTokenUtil;
    private final PersonRepository personRepository;


    public void registerUser(String email, String nickname, String password) {
        if (personRepository.findAllByEmail(email).isPresent())
            throw new IllegalStateException("User already exists");
        personRepository.save(new Person(null, email, nickname, Role.GUEST, BCrypt.hashpw(password, BCrypt.gensalt(10))));
    }

    public ContextHolder getUserForJWT(String jwt) {
        return personRepository.findAllByEmail(jwtTokenUtil.extractEmail(jwt)).orElseThrow(() -> new IllegalArgumentException("Illegal token")).toDto(jwt);
    }

    @Override
    public Person loadUserByUsername(String email) {
        return personRepository.findAllByEmail(email).orElseThrow(() -> new IllegalArgumentException("No such person"));
    }

    public ContextHolder getUserByUserId(Long userId) {
        return personRepository.findById(userId).orElseThrow(() -> new IllegalStateException("No such user present")).toDto(null);
    }
}
