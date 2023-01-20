package com.restful.restful;

import com.restful.restful.model.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestfulApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTest {
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {

	}

	@Test
	public void testGetAllUsers() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/users",
				HttpMethod.GET, entity, String.class);
		System.out.println(response.getBody());
		assertNotNull(response.getBody());
	}

	@Test
	public void testGetUsersById() {
		int id = 2;
		User users = restTemplate.getForObject(getRootUrl() + "/users/" + id , User.class);
		System.out.println(users.getFirstName() + " " + users.getLastName());
		assertNotNull(users);
	}

	@Test
	public void testCreateEmployee() {
		User user = new User();
		user.setEmailId("sezeern@gmail.com");
		user.setFirstName("dasdsa");
		user.setLastName("dasdsadsada");
		user.setAge(24234425);
		ResponseEntity<User> postResponse = restTemplate.postForEntity(getRootUrl() + "/users", user, User.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdateEmployee() {
		int id = 3;
		User user = restTemplate.getForObject(getRootUrl() + "/users/" + id, User.class);
		user.setFirstName("adminewq1");
		user.setLastName("admineqwq2");
		user.setAge(212);
		restTemplate.put(getRootUrl() + "/users/" + id, user);

		User updatedUser = restTemplate.getForObject(getRootUrl() + "/users/" + id, User.class);
		assertNotNull(updatedUser);
	}

	@Test
	public void testDeleteEmployee() {
		int id = 3;
		User user = restTemplate.getForObject(getRootUrl() + "/users/" + id, User.class);
		assertNotNull(user);

		restTemplate.delete(getRootUrl() + "/users/" + id);

		try {
			user = restTemplate.getForObject(getRootUrl() + "/users/" + id, User.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}
}
