package com.funnygorilla.studentmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication(scanBasePackages = {"com.funnygorilla.studentmanagement"})
@OpenAPIDefinition(info = @Info(title = "Student Management APIs", version = "0.0.1", description = "How are you?"))
public class StudentManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementApplication.class, args);
	}
}
