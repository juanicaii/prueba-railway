package com.tecnico.accenture.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.tecnico.accenture" })
public class AccentureApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(AccentureApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Aplicación iniciada correctamente...");
	}

}
