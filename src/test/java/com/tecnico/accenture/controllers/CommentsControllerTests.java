package com.tecnico.accenture.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.tecnico.accenture.models.Comment;
import com.tecnico.accenture.models.Post;
import com.tecnico.accenture.services.ExternalService;

public class CommentsControllerTests {
	@InjectMocks
	private CommentsController controller;
	@Mock
	private ExternalService service;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getComments_all_ok() {
		Mockito.when(service.getComments(Mockito.anyString(), Mockito.anyString())).thenReturn(listaComentarios());
		
		List<Comment> comments = controller.getComments(null, null);
		
		assertEquals(3, comments.size());
		Mockito.verify(service, Mockito.times(1)).getComments(null, null);
	}
	
	@Test
	public void getComments_filtered_by_name_ok() {
		List<Comment> comentariosFiltrados = listaComentarios().stream().filter(comentario -> comentario.getName().equals("test")).collect(Collectors.toList());
		Mockito.when(service.getComments(Mockito.anyString(), Mockito.anyString())).thenReturn(comentariosFiltrados);
		
		List<Comment> comments = controller.getComments("test", null);
		
		assertEquals(1, comments.size());
		Mockito.verify(service, Mockito.times(1)).getComments("name", "test");
	}
	
	@Test
	public void getComments_filtered_by_user_ok() {
		Mockito.when(service.getUserPosts("1")).thenReturn(listaPosteos());
		List<Comment> comentariosFiltrados = listaComentarios().stream().filter(comentario -> comentario.getPostId().equals("1")).collect(Collectors.toList());
		Mockito.when(service.getComments(Mockito.anyString(), Mockito.anyString())).thenReturn(comentariosFiltrados);
		
		List<Comment> comments = controller.getComments(null, "1");
		
		assertEquals(1, comments.size());
		Mockito.verify(service, Mockito.times(1)).getUserPosts("1");
		Mockito.verify(service, Mockito.times(1)).getComments("postId", "1");
	}
	
	/* Utils */
	private List<Comment> listaComentarios() {
		List<Comment> comentarios = new ArrayList<>();
		
		Comment cmd1 = new Comment();
		cmd1.setId("1");
		cmd1.setName("hola");
		cmd1.setPostId("1");
		
		Comment cmd2 = new Comment();
		cmd2.setId("2");
		cmd2.setName("test");
		cmd2.setPostId("3");
		
		Comment cmd3 = new Comment();
		cmd3.setId("3");
		cmd3.setName("chau");
		cmd3.setPostId("2");
		
		comentarios.add(cmd1);
		comentarios.add(cmd2);
		comentarios.add(cmd3);
		
		return comentarios;
	}
	
	private List<Post> listaPosteos() {
		List<Post> posts = new ArrayList<>();
		
		Post post1 = new Post();
		post1.setId("1");
		
		posts.add(post1);
		
		return posts;
	}
}
