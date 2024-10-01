package com.example.SpringSecurityProject.Dao;

import com.example.SpringSecurityProject.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
