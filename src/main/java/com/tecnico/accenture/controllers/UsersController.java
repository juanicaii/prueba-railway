package com.tecnico.accenture.controllers;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tecnico.accenture.services.ExternalService;

@RestController
public class UsersController {
	@Autowired
	private ExternalService service;
	
	@GetMapping("/usuarios")
	public String getUsers() {
		return service.getUsers();
	}
	
	@GetMapping("/fotos/{userId}")
	public void getUserPictures(@PathParam(value = "userId") String userId) {
		
	}
}