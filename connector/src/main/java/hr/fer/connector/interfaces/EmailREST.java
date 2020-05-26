package hr.fer.connector.interfaces;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("cata-emails")
public interface EmailREST {

//    @PostMapping("/sendEmail")
//    void sendEmail(@RequestBody EmailDto email);
//
//    @GetMapping("/get-sent-emails")
//    void getHistory();
}
