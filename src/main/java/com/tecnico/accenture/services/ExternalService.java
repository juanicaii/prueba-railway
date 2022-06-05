package com.tecnico.accenture.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalService {
	@Value("${external.api.hostname}")
	private String baseUrl;
	@Autowired
	private RestTemplate restTemplate;
	
	/*
		Realiza el pedido de usuarios a la API externa.
		
		NOTA: No se considera el uso de models ya que lo único que hace la API 
	 	es mostrar la información. En caso de tener que utilizar la información 
	 	lo correcto sería el uso de un Adapter para poder obtener un objeto 
	 	modelo que sea parte de nuestro dominio que nos sirva para manipularlo.
	*/
	public String getUsers() {
		ResponseEntity<String> jsonResponse = restTemplate.getForEntity(baseUrl + "users", String.class);
		return jsonResponse.getBody();
	}
	
	/*
	 	Realiza el pedido de albums de un determinado usuario a la API externa.
	*/
	public String getUserAlbums(String userId) {
		ResponseEntity<String> jsonResponse = restTemplate.getForEntity(baseUrl + "users/" + userId + "/albums", String.class);
		return jsonResponse.getBody();
	}
}