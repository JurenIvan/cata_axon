package hr.fer.cata.email.controllers;

import hr.fer.cata.email.service.EmailService;
import hr.fer.connector.dto.email.EmailDto;
import hr.fer.connector.interfaces.EmailREST;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmailsController implements EmailREST {

    private final EmailService emailService;

    @Override
    public void sendEmail(EmailDto email, String accessToken) {
        emailService.sendEmail(email.getReceiver(), email.getContent());
    }

    @Override
    public List<EmailDto> getHistory(String accessToken) {
        return emailService.getHistory();
    }
}
