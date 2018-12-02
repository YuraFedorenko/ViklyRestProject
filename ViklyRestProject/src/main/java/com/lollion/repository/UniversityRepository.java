package com.lollion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lollion.model.University;

public interface UniversityRepository extends JpaRepository<University, Integer> {

	public University findOneByUniversityName(String universityName);
}
