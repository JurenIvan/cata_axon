package hr.fer.cata.email.service;

import hr.fer.connector.dto.email.EmailDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {

    public void sendEmail(String receiver, String content) {
    }

    public List<EmailDto> getHistory() {
        return null;
    }
}
