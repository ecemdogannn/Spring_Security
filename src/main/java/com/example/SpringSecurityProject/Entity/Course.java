package com.example.SpringSecurityProject.Entity;


import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class Course {

    @Id //id alanının bu öğrenci için primary olduğunu söylemek için kullanılıyor.
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Bu anotasyon, JPA ve Hibernate'de birincil anahtar alanının otomatik olarak veri tabanı tarafından üretileceğini belirtir.
    @Column(name="id") //veritabanı sütunuyle eşleştiriyoruz.
    private int id;

    @Column(name="courses_title")
    private String coursesTitle;

    @Column(name="description")
    private String description;

    @Column(name="instructor")
    private String instructor;

    @Column(name="instructor_email")
    private String instructorEmail;

    public Course(){

    }
    public Course(String coursesTitle, String description, String instructor, String instructorEmail) {
        this.coursesTitle = coursesTitle;
        this.description = description;
        this.instructor = instructor;
        this.instructorEmail = instructorEmail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCoursesTitle() {
        return coursesTitle;
    }

    public void setCoursesTitle(String coursesTitle) {
        this.coursesTitle = coursesTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getInstructorEmail() {
        return instructorEmail;
    }

    public void setInstructorEmail(String instructorEmail) {
        this.instructorEmail = instructorEmail;
    }

    @Override
    public String toString() {
        return "Courses{" +
                "id=" + id +
                ", coursesTitle='" + coursesTitle + '\'' +
                ", description='" + description + '\'' +
                ", instructor='" + instructor + '\'' +
                ", instructorEmail='" + instructorEmail + '\'' +
                '}';
    }



}
