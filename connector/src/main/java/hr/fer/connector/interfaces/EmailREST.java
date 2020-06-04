package hr.fer.connector.interfaces;

import hr.fer.connector.dto.email.EmailDto;
import hr.fer.connector.model.ContextHolder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("cata-emails")
public interface EmailREST {

    @GetMapping("/get-sent-emails")
    List<EmailDto> getHistory(@RequestBody ContextHolder contextHolder);
}
