package com.tecnico.accenture.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tecnico.accenture.services.ExternalService;

@RestController
public class CommentsController {
	@Autowired
	private ExternalService service;
	
	@GetMapping("/comments")
	public void getComments() {
		
	}
}