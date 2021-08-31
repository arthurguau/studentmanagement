package com.funnygorilla.studentmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
	private Long id;
	private String firstName;
	private String lastName;
	private String contact;
	private String course;
}
