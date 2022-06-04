package com.tecnico.accenture.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalService {
	@Value("${external.api.hostname}")
	private String baseUrl;
	@Autowired
	private RestTemplate restTemplate;
	
	public void getUsers() {
		ResponseEntity<String> jsonResponse = restTemplate.getForEntity(baseUrl + "users", String.class);
		System.out.println(jsonResponse);
	}
}