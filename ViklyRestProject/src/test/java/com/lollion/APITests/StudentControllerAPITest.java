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
import com.lollion.model.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT, classes = ViklyRestProjectApplication.class)
public class StudentControllerAPITest {

	String url;
	private RestTemplate restTemplate = new RestTemplate();;
	private String creationalURL = "http://localhost:8080/students/add";
	private String requestJson = "{\"name\":\"Yura\"}";
	
	@Before
	public void init() {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
		
		restTemplate.exchange(creationalURL, HttpMethod.POST, entity , Student.class);
	}
	
	@Test
	public void creationStudentTest() {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);

		ResponseEntity<Student> studentEntity = restTemplate.exchange(creationalURL, HttpMethod.POST, entity , Student.class);
		Student student = studentEntity.getBody();
		
		assertThat(student.getName(), Matchers.equalTo("Yura"));
	}
	
	@Test
	public void updateStudentTest() {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> putEntity = new HttpEntity<String>(headers);
		
		url = "http://localhost:8080/students/1?studentName=Valera";
		
		ResponseEntity<Student> studentEntity = restTemplate.exchange(url, HttpMethod.PUT, putEntity , Student.class);
		Student student = studentEntity.getBody();
		
		assertThat(student.getName(), Matchers.equalTo("Valera"));
	}
	
	@Test
	public void getStudentTest() {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> getEntity = new HttpEntity<String>(headers);
		
		url = "http://localhost:8080/students/2";
		
		ResponseEntity<Student> studentEntity = restTemplate.exchange(url, HttpMethod.GET, getEntity , Student.class);
		Student student = studentEntity.getBody();
		
		assertThat(student.getName(), Matchers.equalTo("Yura"));
	}

	@Test
	public void deleteStudentTest() {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> delEntity = new HttpEntity<String>(headers);

		url = "http://localhost:8080/students/1";
		
		ResponseEntity<Student> studentEntity = restTemplate.exchange(url, HttpMethod.GET, delEntity , Student.class);
		Student student = studentEntity.getBody();
		
		assertThat(student.getName(), Matchers.equalTo("Yura"));
	}
}
