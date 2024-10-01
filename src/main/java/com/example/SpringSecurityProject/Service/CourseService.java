package com.example.SpringSecurityProject.Service;

import com.example.SpringSecurityProject.Entity.Course;

import java.util.List;

public interface CourseService {
    List<Course> findAll();
    Course save (Course theCourse);
    Course findById (int theId);
    void deletebyId (int theId);



}
