package com.ahmed.courses_system.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
public class StudentProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	@ElementCollection
	private List<String> courses = new ArrayList<>();

	@OneToOne
	@JoinColumn(name = "student_id")
	private Student student;

	public StudentProfile() {
	}

	public StudentProfile(Student student) {
		this.name = student.getName();
		this.student = student;
		this.courses = new ArrayList<>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getCourses() {
		return courses;
	}

	public void setCourses(List<String> courses) {
		this.courses = courses;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public void addCourse(String course){
		courses.add(course);
	}
}
