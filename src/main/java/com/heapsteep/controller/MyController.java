package com.heapsteep.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class MyController {
	private Logger logger=LoggerFactory.getLogger(MyController.class);
	
	@GetMapping("/api6")
	@CircuitBreaker(name="my-circuit-breaker-1", fallbackMethod="method1")
	public String sampleApi() {
		ResponseEntity<String> responseEntity= new RestTemplate().getForEntity("http://localhost:8081/api5", String.class);
		return responseEntity.getBody();
	}
	
	public String method1(Exception e) {
		return "fallback response";
	}
}
