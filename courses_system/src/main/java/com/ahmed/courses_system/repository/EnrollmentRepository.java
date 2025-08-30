package com.ahmed.courses_system.repository;

import com.ahmed.courses_system.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
	boolean existsByStudentIdAndCourseId(int studentId, int courseId);

	@Query("SELECT e FROM Enrollment e WHERE e.student.id = :studentId AND e.course.id = :courseId")
	Optional<Enrollment> findByStudentIdAndCourseId(@Param("studentId") Integer studentId,@Param("courseId") Integer courseId);

	@Query("SELECT e.grade FROM Enrollment e WHERE e.student.id = :studentId AND e.course.id = :courseId")
	Integer findGradeByStudentIdAndCourseId(@Param("studentId") int studentId,@Param("courseId") int courseId);

}
