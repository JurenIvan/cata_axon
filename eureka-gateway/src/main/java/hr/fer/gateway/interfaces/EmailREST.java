package hr.fer.gateway.interfaces;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("cata-email")
public interface EmailREST {
}
