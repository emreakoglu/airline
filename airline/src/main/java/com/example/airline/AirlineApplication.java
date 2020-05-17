package com.example.airline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.example"})
@ComponentScan({"com.example"})
@EntityScan("com.example.bean")
@EnableJpaRepositories("com.example.repository")
public class AirlineApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirlineApplication.class, args);
	}

}
