package com.tecnico.accenture.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tecnico.accenture.models.Comment;
import com.tecnico.accenture.models.Post;
import com.tecnico.accenture.services.ExternalService;

@RestController
public class CommentsController {
	@Autowired
	private ExternalService service;
	
	/* Obtención de todos los comentarios */
	@GetMapping("/comentarios")
	public List<Comment> getComments(@RequestParam(required = false) String name,
			@RequestParam(required = false) String userId) {
		if(name != null) {
			return service.getComments("name", name);
		} else if(userId != null) {
			List<Post> userPosts = service.getUserPosts(userId);
			List<Comment> comments = new ArrayList<>();
			
			userPosts.forEach(post -> {
				List<Comment> postComments = service.getComments("postId", post.getId());
				comments.addAll(postComments);
			});
			
			return comments;
		}
		
		return service.getComments(null, null);
	}
}