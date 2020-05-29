package hr.fer.cata.email.projections.history;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailDetailsProjection {

    private final EmailRepository emailRepository;


}
