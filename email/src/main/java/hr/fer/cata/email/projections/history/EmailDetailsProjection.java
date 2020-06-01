package hr.fer.cata.email.projections.history;

import hr.fer.connector.api.CancelledTripMailSentEvt;
import hr.fer.connector.interfaces.AuthREST;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailDetailsProjection {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailDetailsProjection.class);

    private final EmailRepository emailRepository;
    private final AuthREST authREST;
    private final JavaMailSender mailSender;

    @EventHandler
    private void on(CancelledTripMailSentEvt evt, @Timestamp Instant timestamp) {
        String email = authREST.getUserByUserId(evt.getUserId()).getEmail();

        SimpleMailMessage simpleMailMessage = createMessage(email, "Trip canceled", evt.getExplanation());
        mailSender.send(simpleMailMessage);
        LOGGER.info("Email sent! ", simpleMailMessage);

        emailRepository.save(new EmailDetails(null, email, evt.getExplanation(), timestamp));
    }

    private SimpleMailMessage createMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        return message;
    }

    public List<EmailDetails> findAll() {
        return emailRepository.findAll();
    }
}
