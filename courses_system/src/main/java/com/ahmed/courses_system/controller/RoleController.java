package com.ahmed.courses_system.controller;

import com.ahmed.courses_system.model.Role;
import com.ahmed.courses_system.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

	private final RoleService roleService;

	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}

	@PostMapping
	public ResponseEntity<String> addRole(@RequestBody Role role){
		roleService.addRole(role);
		return ResponseEntity.status(201).body("Role added");
	}

	@GetMapping
	public List<Role> getAllRoles(){
		return roleService.getAllRoles();
	}

	@PostMapping("/{roleTitle}/student/{studentId}")
	public ResponseEntity<String> assignRoleToStudent(@PathVariable String roleTitle, @PathVariable Integer studentId){
		roleService.assignRoleToStudent(studentId, roleTitle);
		return ResponseEntity.ok().body("Role assigned");
	}

	@PostMapping("/{roleTitle}/teacher/{teacherId}")
	public ResponseEntity<String> assignRoleToTeacher(@PathVariable String roleTitle, @PathVariable Integer teacherId){
		roleService.assignRoleToTeacher(teacherId, roleTitle);
		return ResponseEntity.ok().body("Role assigned");
	}

}
