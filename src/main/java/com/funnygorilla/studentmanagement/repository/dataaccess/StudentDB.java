package com.funnygorilla.studentmanagement.repository.dataaccess;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity  
@Table(name = "student")
public class StudentDB implements Serializable {	
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="student_id", updatable = false, nullable = false)
	private Long id;
	
	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name = "CONTACT")
	private String contact;
	
	@Column(name = "COURSE")
	private String course;
}
