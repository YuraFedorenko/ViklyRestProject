package com.lollion.unit;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.lollion.model.University;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ViklyRestProjectApplication.class)
@WebAppConfiguration
public class UniversityControllerTest extends AbstractTest {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();

		String creationalURL = "http://localhost:8080/university/add";
		University university = new University("Oxford");
		String inputJson = super.mapToJson(university);

		mvc.perform(MockMvcRequestBuilders
				.post(creationalURL).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJson));
	}

	@Test
	public void createUniversityTest() throws Exception {

		String creationalURL = "http://localhost:8080/university/add";
		University university = new University("Oxford");
		String inputJson = super.mapToJson(university);

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
				.post(creationalURL).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
	}

	@Test
	public void findUniversityTest() throws Exception {

		String url = "http://localhost:8080/university/1";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
				.get(url).accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	public void updateUniversityTest() throws Exception {

		String url = "http://localhost:8080/university/1?name=Cambridge";

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
				.put(url).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	public void deleteUniversityTest() throws Exception {

		String url = "http://localhost:8080/university/2";

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
				.delete(url).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
}
