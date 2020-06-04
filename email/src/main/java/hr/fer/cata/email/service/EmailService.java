package hr.fer.cata.email.service;

import hr.fer.cata.email.projections.history.EmailDetails;
import hr.fer.cata.email.projections.history.EmailDetailsProjection;
import hr.fer.connector.model.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static hr.fer.connector.model.Role.ADMIN;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailDetailsProjection emailDetailsProjection;

    public List<EmailDetails> getHistory(ContextHolder contextHolder) {
        if (!contextHolder.getRole().equals(ADMIN))
            throw new IllegalStateException("Not authorized");
        return emailDetailsProjection.findAll();
    }
}
