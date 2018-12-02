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

import com.lollion.model.Student;
import com.lollion.model.University;
import com.lollion.service.StudentService;
import com.lollion.service.UniversityService;

@RestController
@RequestMapping(value = "/students")
public class StudentController {

	@Autowired
	StudentService studentService;

	@Autowired
	UniversityService universityService;

	@PostMapping("/add")
	public ResponseEntity<Student> createUniversity(@RequestBody Student student) {

		studentService.createStudent(student);

		return new ResponseEntity<Student>(student, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestParam(required = false) String studentName,
			@RequestParam(required = false) String universityName) {

		Student student = studentService.findStudentById(id);

		if (studentName != null) {
			student.setName(studentName);
		}
		if (universityName != null) {
			student.setUniversity(universityService.findUniversityByName(universityName));
		}
		studentService.updateStudent(student);

		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public Student findStudentById(@PathVariable int id) {

		return studentService.findStudentById(id);
	}

	@GetMapping("/univer")
	public List<Student> findStudentsByUnversity(@RequestParam(required = false) String universityName) {

		University university = universityService.findUniversityByName(universityName);

		return studentService.findStudentsByUniversity(university.getId());
	}

	@GetMapping("/")
	public List<Student> findAllStudents() {

		return studentService.findAllStudents();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Student> deleteStudent(@PathVariable int id) {

		Student student = studentService.findStudentById(id);
		studentService.deleteStudent(student); // it may be done by id

		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}
}
