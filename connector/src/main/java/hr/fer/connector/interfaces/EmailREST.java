package hr.fer.connector.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("cata-emails")
public interface EmailREST {

    @PostMapping("/sendEmail")
    void sendEmail(@RequestBody EmailDto email);

    @GetMapping("/get-sent-emails")
    void getHistory();

}
