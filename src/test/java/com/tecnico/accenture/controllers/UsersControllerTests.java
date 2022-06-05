package com.tecnico.accenture.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.tecnico.accenture.models.Album;
import com.tecnico.accenture.models.Photo;
import com.tecnico.accenture.services.ExternalService;

public class UsersControllerTests {
	@InjectMocks
	private UsersController controller;
	@Mock
	private ExternalService service;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getUsers_ok() {
		Mockito.when(service.getUsers()).thenReturn("[{...}]");
		
		String users = controller.getUsers();
		
		assertEquals("[{...}]", users);
		Mockito.verify(service, Mockito.times(1)).getUsers();
	}
	
	@Test
	public void getUserPhotos_ok() {
		Mockito.when(service.getUserAlbums("1")).thenReturn(listaAlbums());
		Mockito.when(service.getAlbumPhotos("1")).thenReturn(listaFotos());
		
		List<Photo> userPhotos = controller.getUserPictures("1");
		
		assertEquals(2, userPhotos.size());
		Mockito.verify(service, Mockito.times(1)).getUserAlbums("1");
		Mockito.verify(service, Mockito.times(1)).getAlbumPhotos("1");
	}
	
	/* Utils */
	private List<Album> listaAlbums() {
		List<Album> albums = new ArrayList<>();
		
		Album album1 = new Album();
		album1.setId("1");
		
		albums.add(album1);
		
		return albums;
	}
	
	private List<Photo> listaFotos() {
		List<Photo> fotos = new ArrayList<>();
		
		Photo foto1 = new Photo();
		foto1.setAlbumId("1");
		Photo foto2 = new Photo();
		foto2.setAlbumId("1");
		
		fotos.add(foto1);
		fotos.add(foto2);
		
		return fotos;
	}
}