package com.example.SpringSecurityProject.Service;

import com.example.SpringSecurityProject.Dao.CourseRepository;
import com.example.SpringSecurityProject.Entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    private CourseRepository coursesRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository theCoursesRepository) {
        coursesRepository = theCoursesRepository;
    }

    @Override
    public List<Course> findAll() {
      return coursesRepository.findAll();
    }

    @Override
    public Course save(Course theCourse) {
        return coursesRepository.save(theCourse);
    }

    @Override
    public Course findById(int theId) {

            Course theCourse = null;
            Optional<Course> byId = coursesRepository.findById(theId);

            if (byId.isPresent()){
                theCourse = byId.get();
            }
            else{
                throw new RuntimeException("The specified id was not found- " + theId);
            }
           return theCourse;

    }

    @Override
    public void deletebyId(int theId) {
        coursesRepository.deleteById(theId);
    }



}
