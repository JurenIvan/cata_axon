package hr.fer.connector.interfaces;

import hr.fer.connector.dto.email.EmailDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient("cata-emails")
public interface EmailREST {

    @GetMapping("/get-sent-emails")
    List<EmailDto> getHistory(@RequestHeader(value = "Authorization") String accessToken);
}
