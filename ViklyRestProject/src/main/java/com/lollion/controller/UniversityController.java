package com.lollion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lollion.model.University;
import com.lollion.service.UniversityService;

@RestController
@RequestMapping(value = "/university")
public class UniversityController {

	@Autowired
	private UniversityService service;

	@PostMapping("/add")
	public ResponseEntity<University> createUniversity(@RequestBody University university) {

		service.createUniversity(university);

		return new ResponseEntity<University>(university, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<University> updateUniversity(@PathVariable int id,
			@RequestParam(required = false) String name) {

		University university = service.findUniversityById(id);
		university.setUniversityName(name);
		service.updateUniversity(university);

		return new ResponseEntity<University>(university, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public University findUniversityById(@PathVariable int id) {

		return service.findUniversityById(id);
	}
	
	@GetMapping("/")
	public List<University> findAllUniversities() {

		return service.findAllUniversities();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<University> deleteStudent(@PathVariable int id) {
		
		University university = service.findUniversityById(id);
		service.deleteUniversity(university); // it may be done by id
		
		return new ResponseEntity<University>(university, HttpStatus.OK);
	}

}
