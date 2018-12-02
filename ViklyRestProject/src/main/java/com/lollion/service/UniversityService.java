package com.lollion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lollion.model.University;
import com.lollion.repository.UniversityRepository;

@Service
public class UniversityService {
	
	@Autowired
	UniversityRepository universityRepo;

	public void createUniversity(University university) {
		universityRepo.saveAndFlush(university);
	}
	
	public void updateUniversity(University university) {
		universityRepo.save(university);
	}
	
	public University findUniversityById(int id) {
		return universityRepo.findById(id).get();
	}
	
	public List<University> findAllUniversities(){
		return universityRepo.findAll();
	}
	
	public University findUniversityByName(String name) {
		return universityRepo.findOneByUniversityName(name);
	}
	
	public void deleteUniversity(University university) {
		universityRepo.delete(university);
	}
	
}
