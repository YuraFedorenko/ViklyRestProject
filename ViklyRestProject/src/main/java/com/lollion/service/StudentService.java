package com.lollion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lollion.model.Student;
import com.lollion.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepo;
	
	public void createStudent(Student student) {
		studentRepo.saveAndFlush(student);
	}
	
	public void updateStudent(Student student) {
		studentRepo.save(student);
	}
	
	public Student findStudentById(int id) {
		return studentRepo.findById(id).get();
	}
	
	public List<Student> findAllStudents(){
		return studentRepo.findAll();
	}
	
	public void deleteStudent(Student student) {
		studentRepo.delete(student);
	}
	
	public List<Student> findStudentsByUniversity(int universityId){
		return studentRepo.findAllByUniversityId(universityId);
	}

}
