package com.funnygorilla.studentmanagement.controllers.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.funnygorilla.studentmanagement.dto.StudentDTO;

public interface StudentAPI {
	
	@RequestMapping(method = RequestMethod.GET, value="/")
	public ResponseEntity<List<StudentNumber>> getStudents();
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<Student> getStudentByID(@PathVariable(required=true) long id);
	
	@RequestMapping(method = RequestMethod.POST, value="/")
	public ResponseEntity<StudentNumber> createStudent(@RequestBody @Valid Student student);
	
    @RequestMapping(method = RequestMethod.PUT, value="/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable("id")Long id, 
			@Valid @RequestBody Student student);
	
	@RequestMapping(method = RequestMethod.DELETE, value="/{id}")
	public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") Long id);
}
