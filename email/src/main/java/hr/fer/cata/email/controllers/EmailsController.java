package hr.fer.cata.email.controllers;

import hr.fer.cata.email.service.EmailService;
import hr.fer.connector.dto.email.EmailDto;
import hr.fer.connector.interfaces.EmailREST;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class EmailsController implements EmailREST {

    private final EmailService emailService;

    @Override
    public List<EmailDto> getHistory(String accessToken) {
        return emailService.getHistory().stream().map(e -> new EmailDto(e.getReceiver(), e.getContent(), e.getTime())).collect(toList());
    }
}
