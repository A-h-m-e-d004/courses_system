package com.ahmed.courses_system.service;

import com.ahmed.courses_system.dtos.TeacherDto;
import com.ahmed.courses_system.dtos.TeacherResponseDto;
import com.ahmed.courses_system.mapper.TeacherMapper;
import com.ahmed.courses_system.model.Role;
import com.ahmed.courses_system.model.Teacher;
import com.ahmed.courses_system.repository.RoleRepository;
import com.ahmed.courses_system.repository.TeacherRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

	private final TeacherRepository teacherRepository;

	private final PasswordEncoder passwordEncoder;

	private final TeacherMapper teacherMapper;

	private final RoleService roleService;

	private final RoleRepository repository;

	public TeacherService(TeacherRepository teacherRepository, PasswordEncoder passwordEncoder, TeacherMapper teacherMapper, RoleService roleService, RoleRepository repository) {
		this.teacherRepository = teacherRepository;
		this.passwordEncoder = passwordEncoder;
		this.teacherMapper = teacherMapper;
		this.roleService = roleService;
		this.repository = repository;
	}

	public void addTeacher(TeacherDto teacherDto){
		if (teacherRepository.existsByEmail(teacherDto.email())){
			throw new RuntimeException("Teacher already exist");
		}
		var teacher = teacherMapper.toTeacher(teacherDto);
		teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
		if (!repository.existsByTitle("ADMIN")){
			roleService.addRole(new Role("ADMIN"));
		}
		teacherRepository.save(teacher);
		roleService.assignRoleToTeacher(teacher.getId(), "ADMIN");
	}

	public List<TeacherResponseDto> getAllTeacher(){
		return teacherRepository.findAll().stream().map(teacher -> new TeacherResponseDto(teacher.getId(), teacher.getName())).toList();
	}


}
