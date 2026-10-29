
package com.company.eduplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.eduplatform.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
