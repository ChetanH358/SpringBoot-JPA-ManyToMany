package com.demo.many_to_many.entity;

import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Course {

	@Id
	private int c_Id;
	private String c_Title;
	private int c_Modules;
	private double c_Fees;

	/*
	 * we are telling to course table that everthing foregin key should be mapped by
	 * student which means Student -> parent & course -> child tables
	 * 
	 * using mapped by we can tell to Couses that your parent is Student table and
	 * it will handle all the primary key managment
	 */

	@ManyToMany(mappedBy = "courses")
	private Set<Student> students;

	public int getC_Id() {
		return c_Id;
	}

	public void setC_Id(int c_Id) {
		this.c_Id = c_Id;
	}

	public String getC_Title() {
		return c_Title;
	}

	public void setC_Title(String c_Title) {
		this.c_Title = c_Title;
	}

	public int getC_Modules() {
		return c_Modules;
	}

	public void setC_Modules(int c_Modules) {
		this.c_Modules = c_Modules;
	}

	public double getC_Fees() {
		return c_Fees;
	}

	public void setC_Fees(double c_Fees) {
		this.c_Fees = c_Fees;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

}
