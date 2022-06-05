package com.tecnico.accenture.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tecnico.accenture.models.Album;
import com.tecnico.accenture.models.Photo;
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
	public List<Photo> getUserPictures(@PathVariable String userId) {
		List<Album> userAlbums = service.getUserAlbums(userId);
		List<Photo> userPhotos = new ArrayList<>();
		
		//Recorremos todos los albums del usuario y agregamos las fotos de c/u
		userAlbums.forEach(album -> {
			List<Photo> albumPhotos = service.getAlbumPhotos(album.getId());
			userPhotos.addAll(albumPhotos);
		});
		
		return userPhotos;
	}
}