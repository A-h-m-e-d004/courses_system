package com.ahmed.courses_system.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Enrollment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer grade;
	private LocalDate enrolledAt;

	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;

	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;

	public Enrollment() {
	}

	public Enrollment(Student student, Course course) {
		this.student = student;
		this.course = course;
		this.enrolledAt = LocalDate.now();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public LocalDate getEnrolledAt() {
		return enrolledAt;
	}

	public void setEnrolledAt(LocalDate enrolledAt) {
		this.enrolledAt = enrolledAt;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
}
