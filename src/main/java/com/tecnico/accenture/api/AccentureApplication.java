package com.tecnico.accenture.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AccentureApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccentureApplication.class, args);
		
		System.out.println("Aplicación iniciada correctamente...");	
	}

}
