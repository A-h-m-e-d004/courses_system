package com.ahmed.courses_system.service;

import com.ahmed.courses_system.model.Role;
import com.ahmed.courses_system.model.Student;
import com.ahmed.courses_system.model.Teacher;
import com.ahmed.courses_system.repository.RoleRepository;
import com.ahmed.courses_system.repository.StudentRepository;
import com.ahmed.courses_system.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

	private final RoleRepository roleRepository;

	private final StudentRepository studentRepository;

	private final TeacherRepository teacherRepository;

	public RoleService(RoleRepository roleRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
		this.roleRepository = roleRepository;
		this.studentRepository = studentRepository;
		this.teacherRepository = teacherRepository;
	}

	public void addRole(Role role){
		roleRepository.save(role);
	}

	public List<Role> getAllRoles(){
		return roleRepository.findAll();
	}

	public void assignRoleToStudent(Integer studentId, String roleTitle){
		Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
		Role role = roleRepository.findByTitle(roleTitle).orElseThrow(() -> new RuntimeException("Role not found"));
		student.getRoles().add(role);
		studentRepository.save(student);
	}

	public void assignRoleToTeacher(Integer teacherId, String roleTitle){
		Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() -> new RuntimeException("Teacher not found"));
		Role role = roleRepository.findByTitle(roleTitle).orElseThrow(() -> new RuntimeException("Role not found"));
		teacher.getRoles().add(role);
		teacherRepository.save((teacher));
	}
}
