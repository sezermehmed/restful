package com.restful.restful;

import com.restful.restful.model.Employer;
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
public class EmployerControllerIntegrationTest {
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
	public void testGetAllEmployer() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/employers",
				HttpMethod.GET, entity, String.class);
		System.out.println(response.getBody());
		assertNotNull(response.getBody());
	}

	@Test
	public void testGetEmployerById() {
		int id = 2;
		Employer employer = restTemplate.getForObject(getRootUrl() + "/employers/" + id , Employer.class);
		System.out.println(employer.getFirstName() + " " + employer.getLastName());
		assertNotNull(employer);
	}

	@Test
	public void testCreateEmployer() {
		Employer employer = new Employer();
		employer.setOtherInfos("sezersezer@gmail.com");
		employer.setFirstName("sezer");
		employer.setLastName("adminadmin");

		ResponseEntity<Employer> postResponse = restTemplate.postForEntity(getRootUrl() + "/employers", employer, Employer.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdateEmployer() {
		int id = 4;
		Employer employer = restTemplate.getForObject(getRootUrl() + "/employers/" + id, Employer.class);
		employer.setFirstName("sezersez");
		employer.setLastName("adminse");
		employer.setOtherInfos("Infosdasasdsad....");

		restTemplate.put(getRootUrl() + "/employers/" + id, employer);

		Employer updatedEmployer = restTemplate.getForObject(getRootUrl() + "/employers/" + id, Employer.class);
		assertNotNull(updatedEmployer);
	}

	@Test
	public void testDeleteEmployer() {
		int id = 4;
		Employer employer = restTemplate.getForObject(getRootUrl() + "/employers/" + id, Employer.class);
		assertNotNull(employer);

		restTemplate.delete(getRootUrl() + "/employers/" + id);

		try {
			employer = restTemplate.getForObject(getRootUrl() + "/employers/" + id, Employer.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}
}
