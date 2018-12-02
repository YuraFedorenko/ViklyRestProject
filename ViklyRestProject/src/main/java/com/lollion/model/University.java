package com.lollion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class University {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="university_id")
	private int id;
	
	@Column(name="university_name")
	private String universityName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public University() {
	}

	public University(String universityName) {
		this.universityName = universityName;
	}

	public String getUniversityName() {
		return universityName;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		University other = (University) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "University [id=" + id + ", universityName=" + universityName + "]";
	}

}
