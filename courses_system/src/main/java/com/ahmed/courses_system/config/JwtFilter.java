package com.ahmed.courses_system.config;

import com.ahmed.courses_system.repository.StudentRepository;
import com.ahmed.courses_system.repository.TeacherRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Component
public class JwtFilter extends OncePerRequestFilter {

	private final JwtUtil jwtUtil;

	private final StudentRepository studentRepository;

	private final TeacherRepository teacherRepository;

	public JwtFilter(JwtUtil jwtUtil, StudentRepository studentRepository, TeacherRepository teacherRepository) {
		this.jwtUtil = jwtUtil;
		this.studentRepository = studentRepository;
		this.teacherRepository = teacherRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String header = request.getHeader("Authorization");
		if (header != null && header.startsWith("bearer")){
			String token = header.substring(7);
			if (jwtUtil.isTokenValid(token)){
				String userType = jwtUtil.extractUserType(token);
				String username = jwtUtil.extractUsername(token);
				if (Objects.equals(userType, "STUDENT")){
					var student = studentRepository.findByName(username);
					if (student.isPresent()) {
						List<SimpleGrantedAuthority> authorities = student.get().getRoles().stream()
								.map(role -> new SimpleGrantedAuthority("ROLE_" + role.getTitle())).toList();
						UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null, authorities);
						SecurityContextHolder.getContext().setAuthentication(auth);
					}
				} else if (Objects.equals(userType, "TEACHER")) {
					var teacher = teacherRepository.findByName(username);
					if (teacher.isPresent()){
						List<SimpleGrantedAuthority> authorities = teacher.get().getRoles().stream()
								.map(role -> new SimpleGrantedAuthority("ROLE_" + role.getTitle())).toList();
						UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null, authorities);
						SecurityContextHolder.getContext().setAuthentication(auth);
					}
				}
			}
		}
		filterChain.doFilter(request, response);
	}
}
