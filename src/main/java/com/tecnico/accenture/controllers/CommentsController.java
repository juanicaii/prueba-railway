package com.tecnico.accenture.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tecnico.accenture.models.Post;
import com.tecnico.accenture.services.ExternalService;

@RestController
public class CommentsController {
	@Autowired
	private ExternalService service;
	
	/* Obtención de todos los comentarios */
	@GetMapping("/comentarios")
	public String getComments(@RequestParam(required = false) String name,
			@RequestParam(required = false) String userId) {
		if(name != null) {
			return service.getCommentsByName(name);
		} else if(userId != null) {
			List<Post> userPosts = service.getUserPosts(userId);
		}
		
		return service.getComments();
	}
}