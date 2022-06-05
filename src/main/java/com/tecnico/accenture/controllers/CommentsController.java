package com.tecnico.accenture.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tecnico.accenture.services.ExternalService;

@RestController
public class CommentsController {
	@Autowired
	private ExternalService service;
	
	/* Obtención de todos los comentarios */
	@GetMapping("/comentarios")
	public String getComments(@RequestParam(required = false) String name) {
		if(name != null) {
			return service.getCommentsByName(name);
		}
		
		return service.getComments();
	}
	
	@GetMapping("/comentarios/{userId}")
	public void getCommentsByUser(@PathVariable String userId) {
		
	}
}