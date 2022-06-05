package com.tecnico.accenture.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tecnico.accenture.services.ExternalService;

@RestController
public class CommentsController {
	@Autowired
	private ExternalService service;
	
	/* Obtención de todos los comentarios */
	@GetMapping("/comentarios")
	public String getComments() {
		return service.getComments();
	}
	
	@GetMapping("/comentarios/{name}")
	public void getCommentsByName(@PathVariable String name) {
		
	}
	
	@GetMapping("/comentarios/{userId}")
	public void getCommentsByUser(@PathVariable String userId) {
		
	}
}