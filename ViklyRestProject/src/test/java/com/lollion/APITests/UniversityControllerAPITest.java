package com.lollion.APITests;

import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.lollion.ViklyRestProjectApplication;
import com.lollion.model.University;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT, classes = ViklyRestProjectApplication.class)
public class UniversityControllerAPITest {
	
	String url;
	private RestTemplate restTemplate = new RestTemplate();;
	private String creationalURL = "http://localhost:8080/university/add";
	private String requestJson = "{\"universityName\":\"Oxford\"}";
	
	@Before
	public void init() {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
		
		restTemplate.exchange(creationalURL, HttpMethod.POST, entity , University.class);
	}
	
	@Test
	public void creationUniversityTest() {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
		
		
		
		ResponseEntity<University> universityEntity = restTemplate.exchange(creationalURL, HttpMethod.POST, entity , University.class);
		University university = universityEntity.getBody();
		
		assertThat(university.getUniversityName(), Matchers.equalTo("Oxford"));
	}
	
	@Test
	public void puttingUniversityTest() {
		
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> putEntity = new HttpEntity<String>(headers);
		
		url = "http://localhost:8080/university/1?name=Cambridge";
		
		ResponseEntity<University> universityEntity = restTemplate.exchange(url, HttpMethod.PUT, putEntity , University.class);
		University university = universityEntity.getBody();
		
		assertThat(university.getUniversityName(), Matchers.equalTo("Cambridge"));
	}
	
	@Test
	public void getUniversityTest() {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> getEntity = new HttpEntity<String>(headers);
		
		url = "http://localhost:8080/university/1";
		
		ResponseEntity<University> universityEntity = restTemplate.exchange(url, HttpMethod.GET, getEntity , University.class);
		University university = universityEntity.getBody();
		
		assertThat(university.getUniversityName(), Matchers.equalTo("Cambridge"));
	}

	@Test
	public void deleteUniversityTest() {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> delEntity = new HttpEntity<String>(headers);

		url = "http://localhost:8080/university/2";
		
		ResponseEntity<University> universityEntity = restTemplate.exchange(url, HttpMethod.GET, delEntity , University.class);
		University university = universityEntity.getBody();
		
		assertThat(university.getUniversityName(), Matchers.equalTo("Oxford"));
	}
	
}

