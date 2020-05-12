package hr.fer.connector.interfaces;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("cata-emails")
public interface EmailREST {
}
