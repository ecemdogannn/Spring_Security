package com.example.SpringSecurityProject.Controller;

import com.example.SpringSecurityProject.Entity.Course;
import com.example.SpringSecurityProject.Service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coursesApi")
public class CourseController {
    private CourseService courseService;

    public CourseController(CourseService theCourseService){
        courseService = theCourseService;
    }

    //kursları listeler
    @GetMapping("/courses") //HTTP GET isteklerini "/coursesApi/courses" path'ine yönlendirir.
    public List<Course> findAll() {
        return courseService.findAll();
    }

    //kurs ekler
    @PostMapping("/courses")
    public Course addCourses(@RequestBody Course theCourse){
        theCourse.setId(0);
        Course dbCourse = courseService.save(theCourse);
        return dbCourse;
    }

    //belirli kursu getir
    @GetMapping("/courses/{coursesId}")
    public Course getCourses(@PathVariable int coursesId){
        Course theCourse = courseService.findById(coursesId);

        //kurs bulunamazsa bir hata mesajı fırlatsın
        if (theCourse == null){
            throw new RuntimeException(" no course id - " + coursesId);
        }
        return theCourse;
    }

    //kursu sil
    @DeleteMapping("/courses/{coursesId}")
    public String deleteCourses(@PathVariable int coursesId){

        Course theCourse = courseService.findById(coursesId);
        if (theCourse == null){
            throw new RuntimeException(" no course id - "+ coursesId);

        }
        courseService.deletebyId(coursesId);
        return "delete courses id - " + coursesId;
    }

    //mevcut kursu güncelle
    @PutMapping("/courses")
    public Course updateCourses(@RequestBody Course theCourse){

        //Güncellenmiş kursu veri tabanına kaydeder.
        Course dbCourse = courseService.save(theCourse);
        return dbCourse; //güncellenmiş kursu döndürür
    }



}

