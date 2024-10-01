package com.example.SpringSecurityProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication


public class SpringSecurityProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityProjectApplication.class, args);

		// BCrypt şifreleme kodu
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String rawPassword1 = "1213test";
		String rawPassword2 = "test1819*";

		String encodedPassword1 = encoder.encode(rawPassword1);
		String encodedPassword2 = encoder.encode(rawPassword2);

		System.out.println("BCrypt encoded password for 'admin': " + encodedPassword1);
		System.out.println("BCrypt encoded password for 'user': " + encodedPassword2);
	}

}


