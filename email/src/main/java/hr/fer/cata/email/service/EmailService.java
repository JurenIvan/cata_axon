package hr.fer.cata.email.service;

import hr.fer.cata.email.projections.history.EmailDetails;
import hr.fer.cata.email.projections.history.EmailDetailsProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailDetailsProjection emailDetailsProjection;

    public List<EmailDetails> getHistory() {
        return emailDetailsProjection.findAll();
    }

    public void sendEmailValidations(String receiver, String content) {
        if (!isValidEmailAddress(receiver))
            throw new IllegalArgumentException("Illegal email adress");
        if (content.isBlank())
            throw new IllegalArgumentException("Empty content");
    }

    private boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
}
