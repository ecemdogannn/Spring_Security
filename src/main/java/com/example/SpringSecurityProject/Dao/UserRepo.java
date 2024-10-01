package com.example.SpringSecurityProject.Dao;

import com.example.SpringSecurityProject.Entity.UserCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserCourse, String> {
}
