package com.funnygorilla.studentmanagement.controllers.api;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
	private Long id;
	
	@NotNull(message = "First Name can not be null.")
	@Size(min = 5, max = 10, message = "the lenth of name must be between 1 and 10 characters.")
	private String firstName;
	
	@NotNull(message = "Last Name can not be null.")
	@Size(min = 5, max = 10, message = "the lenth of name must be between 1 and 10 characters.")
	private String lastName;

	@NotNull(message = "Contact can not be null.")
	private String contact;
	
	@NotNull(message = "Course can not be null.")
	private String course;
}
