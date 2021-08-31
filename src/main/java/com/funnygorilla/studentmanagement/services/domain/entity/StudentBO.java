package com.funnygorilla.studentmanagement.services.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentBO {
	private Long id;
	private String firstName;
	private String lastName;
	private String contact;
	private String course;
}
