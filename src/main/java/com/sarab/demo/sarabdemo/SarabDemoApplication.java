package com.sarab.demo.sarabdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.sarab.demo.sarabdemo.model.Greeting;
import com.sarab.demo.sarabdemo.repository.GreetingRepository;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class SarabDemoApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(SarabDemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SarabDemoApplication.class, args);
	}
	
	@Bean
	  CommandLineRunner loadDatabase(GreetingRepository repository) {
	    return args -> {
	      logger.debug("loading database..");
	      repository.save(new Greeting(1, "Hello"));
	      repository.save(new Greeting(2, "Hola"));
	      repository.save(new Greeting(3, "Ohai"));
	      logger.debug("record count: {}", repository.count());
	      repository.findAll().forEach(x -> logger.debug(x.toString()));
	    };

	  }
}
