package hr.fer.connector.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("cata-trips")
public interface TripsREST {

    @RequestMapping("/trip/{applicationName}")
    String example1(@PathVariable String applicationName);

}
