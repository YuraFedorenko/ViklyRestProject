package com.lollion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lollion.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{
	
	public List<Student> findAllByUniversityId(int universityId);
}
