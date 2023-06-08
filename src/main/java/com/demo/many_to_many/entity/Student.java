package com.demo.many_to_many.entity;

import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Student {
	@Id
	private int std_Id;
	private String std_Name;
	private int std_Age;
	private String std_Dept;

	@ManyToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	private Set<Course> courses;

	public int getStd_Id() {
		return std_Id;
	}

	public void setStd_Id(int std_Id) {
		this.std_Id = std_Id;
	}

	public String getStd_Name() {
		return std_Name;
	}

	public void setStd_Name(String std_Name) {
		this.std_Name = std_Name;
	}

	public int getStd_Age() {
		return std_Age;
	}

	public void setStd_Age(int std_Age) {
		this.std_Age = std_Age;
	}

	public String getStd_Dept() {
		return std_Dept;
	}

	public void setStd_Dept(String std_Dept) {
		this.std_Dept = std_Dept;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

}
