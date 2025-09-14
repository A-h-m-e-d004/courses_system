package com.ahmed.courses_system.repository;

import com.ahmed.courses_system.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	 Optional<Role> findByTitle(String title);
	 boolean existsByTitle(String title);
}
