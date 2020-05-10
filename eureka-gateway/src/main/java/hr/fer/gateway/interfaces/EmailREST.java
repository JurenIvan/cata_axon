package hr.fer.gateway.interfaces;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("cata-emails")
public interface EmailREST {
}
