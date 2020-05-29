package hr.fer.cata.trips.service;

import hr.fer.connector.interfaces.AuthREST;
import hr.fer.connector.model.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ContextService implements UserDetailsService {

    private final AuthREST authREST;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public ContextHolder getLoggedIn() {
        return (ContextHolder) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
