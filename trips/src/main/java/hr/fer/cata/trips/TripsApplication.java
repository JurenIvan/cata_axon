package hr.fer.cata.trips;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TripsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TripsApplication.class, args);
	}

}
