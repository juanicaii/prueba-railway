package com.tecnico.accenture.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.tecnico.accenture.services.ExternalService;

@SpringBootApplication
@ComponentScan(basePackages = { "com.tecnico.accenture" })
public class AccentureApplication implements CommandLineRunner {
	@Autowired
	private ExternalService externalService;
	
	public static void main(String[] args) {
		SpringApplication.run(AccentureApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Aplicación iniciada correctamente...");
		externalService.getUsers();
	}

}
