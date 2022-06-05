package com.tecnico.accenture.services;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.tecnico.accenture.models.Album;
import com.tecnico.accenture.models.Comment;
import com.tecnico.accenture.models.Photo;
import com.tecnico.accenture.models.Post;

public class ExternalServiceTests {
	@InjectMocks
	private ExternalService service;
	@Mock
	private RestTemplate restTemplate;
	
	private static final String USER_ALBUMS_RESPONSE = "[{\"postId\":1,\"id\":1,\"name\":\"id labore ex et quam laborum\",\"email\":\"Eliseo@gardner.biz\",\"body\":\"laudantium enim quasi est quidem magnam voluptate ipsam eos\\ntempora quo necessitatibus\\ndolor quam autem quasi\\nreiciendis et nam sapiente accusantium\"},{\"postId\":1,\"id\":2,\"name\":\"quo vero reiciendis velit similique earum\",\"email\":\"Jayne_Kuhic@sydney.com\",\"body\":\"est natus enim nihil est dolore omnis voluptatem numquam\\net omnis occaecati quod ullam at\\nvoluptatem error expedita pariatur\\nnihil sint nostrum voluptatem reiciendis et\"},{\"postId\":1,\"id\":3,\"name\":\"odio adipisci rerum aut animi\",\"email\":\"Nikita@garfield.biz\",\"body\":\"quia molestiae reprehenderit quasi aspernatur\\naut expedita occaecati aliquam eveniet laudantium\\nomnis quibusdam delectus saepe quia accusamus maiores nam est\\ncum et ducimus et vero voluptates excepturi deleniti ratione\"}]\r\n";
	private static final String USER_POSTS_RESPONSE = "[{\"userId\":1,\"id\":1,\"title\":\"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\",\"body\":\"quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto\"}]\r\n";
	private static final String ALBUM_PHOTOS_RESPONSE = "[{\"albumId\":1,\"id\":1,\"title\":\"accusamus beatae ad facilis cum similique qui sunt\",\"url\":\"https://via.placeholder.com/600/92c952\",\"thumbnailUrl\":\"https://via.placeholder.com/150/92c952\"},{\"albumId\":1,\"id\":2,\"title\":\"reprehenderit est deserunt velit ipsam\",\"url\":\"https://via.placeholder.com/600/771796\",\"thumbnailUrl\":\"https://via.placeholder.com/150/771796\"},{\"albumId\":1,\"id\":3,\"title\":\"officia porro iure quia iusto qui ipsa ut modi\",\"url\":\"https://via.placeholder.com/600/24f355\",\"thumbnailUrl\":\"https://via.placeholder.com/150/24f355\"},{\"albumId\":1,\"id\":4,\"title\":\"culpa odio esse rerum omnis laboriosam voluptate repudiandae\",\"url\":\"https://via.placeholder.com/600/d32776\",\"thumbnailUrl\":\"https://via.placeholder.com/150/d32776\"},{\"albumId\":1,\"id\":5,\"title\":\"natus nisi omnis corporis facere molestiae rerum in\",\"url\":\"https://via.placeholder.com/600/f66b97\",\"thumbnailUrl\":\"https://via.placeholder.com/150/f66b97\"}]\r\n";
	private static final String COMMENTS = "[{\"postId\":1,\"id\":1,\"name\":\"id labore ex et quam laborum\",\"email\":\"Eliseo@gardner.biz\",\"body\":\"laudantium enim quasi est quidem magnam voluptate ipsam eos\\ntempora quo necessitatibus\\ndolor quam autem quasi\\nreiciendis et nam sapiente accusantium\"},{\"postId\":1,\"id\":2,\"name\":\"quo vero reiciendis velit similique earum\",\"email\":\"Jayne_Kuhic@sydney.com\",\"body\":\"est natus enim nihil est dolore omnis voluptatem numquam\\net omnis occaecati quod ullam at\\nvoluptatem error expedita pariatur\\nnihil sint nostrum voluptatem reiciendis et\"}]\r\n";
	private static final String FILTERED_COMMENTS = "[{\"postId\":1,\"id\":1,\"name\":\"id labore ex et quam laborum\",\"email\":\"Eliseo@gardner.biz\",\"body\":\"laudantium enim quasi est quidem magnam voluptate ipsam eos\\ntempora quo necessitatibus\\ndolor quam autem quasi\\nreiciendis et nam sapiente accusantium\"}]\r\n";
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(service, "gson", new Gson());
	}
	
	@Test
	public void getUsers_ok() {
		Mockito.when(restTemplate.getForEntity(Mockito.anyString(), Mockito.eq(String.class))).thenReturn(new ResponseEntity<String>("test", HttpStatus.OK));
		
		String response = service.getUsers();
		
		assertEquals("test", response);
		Mockito.verify(restTemplate, Mockito.times(1)).getForEntity(Mockito.anyString(), Mockito.eq(String.class));
	}
	
	@Test
	public void getUserAlbums_ok() {
		Mockito.when(restTemplate.getForEntity(Mockito.anyString(), Mockito.eq(String.class))).thenReturn(new ResponseEntity<String>(USER_ALBUMS_RESPONSE, HttpStatus.OK));
		
		List<Album> response = service.getUserAlbums("1");
		
		assertEquals(3, response.size());
		Mockito.verify(restTemplate, Mockito.times(1)).getForEntity(Mockito.anyString(), Mockito.eq(String.class));
	}
	
	@Test
	public void getAlbumPhotos_ok() {
		Mockito.when(restTemplate.getForEntity(Mockito.anyString(), Mockito.eq(String.class))).thenReturn(new ResponseEntity<String>(ALBUM_PHOTOS_RESPONSE, HttpStatus.OK));
		
		List<Photo> response = service.getAlbumPhotos("1");
		
		assertEquals(5, response.size());
		Mockito.verify(restTemplate, Mockito.times(1)).getForEntity(Mockito.anyString(), Mockito.eq(String.class));
	}
	
	@Test
	public void getUserPosts_ok() {
		Mockito.when(restTemplate.getForEntity(Mockito.anyString(), Mockito.eq(String.class))).thenReturn(new ResponseEntity<String>(USER_POSTS_RESPONSE, HttpStatus.OK));
		
		List<Post> response = service.getUserPosts("1");
		
		assertEquals(1, response.size());
		Mockito.verify(restTemplate, Mockito.times(1)).getForEntity(Mockito.anyString(), Mockito.eq(String.class));
	}
	
	@Test
	public void getComments_withoutFilter() {
		Mockito.when(restTemplate.getForEntity(Mockito.anyString(), Mockito.eq(String.class))).thenReturn(new ResponseEntity<String>(COMMENTS, HttpStatus.OK));
		
		List<Comment> response = service.getComments(null, null);
		
		assertEquals(2, response.size());
		Mockito.verify(restTemplate, Mockito.times(1)).getForEntity(Mockito.anyString(), Mockito.eq(String.class));
	}
	
	@Test
	public void getComments_withFilter() {
		Mockito.when(restTemplate.getForEntity(Mockito.anyString(), Mockito.eq(String.class))).thenReturn(new ResponseEntity<String>(FILTERED_COMMENTS, HttpStatus.OK));
		
		List<Comment> response = service.getComments("name", "hola");
		
		assertEquals(1, response.size());
		Mockito.verify(restTemplate, Mockito.times(1)).getForEntity(Mockito.anyString(), Mockito.eq(String.class));
	}
}