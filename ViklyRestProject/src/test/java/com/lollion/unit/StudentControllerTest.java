package com.lollion.unit;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.lollion.AbstractTest;
import com.lollion.ViklyRestProjectApplication;
import com.lollion.model.Student;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ViklyRestProjectApplication.class)
@WebAppConfiguration
public class StudentControllerTest extends AbstractTest{

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
		
		String creationalURL = "http://localhost:8080/students/add";
		Student student = new Student("Yura");
		String inputJson = super.mapToJson(student);

		mvc.perform(MockMvcRequestBuilders.post(creationalURL)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
	}

	@Test
	public void createStudentTest() throws Exception {

		String creationalURL = "http://localhost:8080/students/add";
		Student student = new Student("Yura");
		String inputJson = super.mapToJson(student);

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(creationalURL)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
	}
	
	@Test
	public void updateStudentTest() throws Exception {
		
		String url = "http://localhost:8080/students/2?studentName=Valera";

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(url)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void findStudentTest() throws Exception {
		
		String url = "http://localhost:8080/students/2";
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(url)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		
	}
	
	@Test
	public void deleteStudentTest() throws Exception {
		
		String url = "http://localhost:8080/students/1";
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(url)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

}
