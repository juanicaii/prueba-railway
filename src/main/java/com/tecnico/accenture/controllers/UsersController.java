package com.tecnico.accenture.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tecnico.accenture.services.ExternalService;

@RestController
public class UsersController {
	@Autowired
	private ExternalService service;
	
	/*
	 	Consulta de usuarios
	*/
	@GetMapping("/usuarios")
	public String getUsers() {
		return service.getUsers();
	}
	
	/*
	 	Consulta de las fotos de un determinado usuario
	*/
	@GetMapping("/fotos/{userId}")
	public String getUserPictures(@PathVariable String userId) {
		return service.getUserAlbums(userId);
	}
}