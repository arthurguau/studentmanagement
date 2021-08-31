package com.funnygorilla.studentmanagement.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.funnygorilla.studentmanagement.controllers.api.Student;
import com.funnygorilla.studentmanagement.controllers.api.StudentAPI;
import com.funnygorilla.studentmanagement.controllers.api.StudentNumber;
import com.funnygorilla.studentmanagement.dto.StudentDTO;
import com.funnygorilla.studentmanagement.services.StudentService;

@RestController
@RequestMapping(
	path = "/api/v1/students",
	produces = APPLICATION_JSON_UTF8_VALUE)   
public class StudentController implements StudentAPI {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired  
	private StudentService studentService;
	
	@Override
	public ResponseEntity<List<StudentNumber>> getStudents() {
		
		List <StudentNumber> studentNumbers = new ArrayList<StudentNumber>();
		
		List <StudentDTO> students = this.studentService.getAllStudents();	
		
		if (students == null || students.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		logger.debug(" --> " + " getStudents() returned: \n" + students.toString());
		
		// compose response: DTO -> Response
		StudentNumber number = null;
		Iterator<StudentDTO> ite = students.iterator();
		while (ite.hasNext()) {
			number = Converter.fromStudentDTOToStudentNumber(ite.next());			
			studentNumbers.add(number);
		}
		
		return new ResponseEntity<>(studentNumbers, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Student> getStudentByID(@PathVariable(required=true) long id) {
		
		StudentDTO sdto = this.studentService.getStudentByID(id);
		logger.debug(" --> " + " getStudentByID() " + sdto.toString());
		
		if (sdto == null || sdto.getId() == null){
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
		} 
		
		Student s = Converter.fromStudentDTOToStudent(sdto);
		return new ResponseEntity<>(s, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<StudentNumber> createStudent(@RequestBody @Valid Student student) {
		
		StudentDTO sdto = Converter.fromStudentToStudentDTO(student);
		
        Long id = this.studentService.createStudent(sdto);
        logger.debug(" --> " + " student with " + id+ " created." );
        
        
        StudentNumber response = new StudentNumber();
        response.setId(id);
        
        return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
    @Override
	public ResponseEntity<Student> updateStudent(@PathVariable("id") Long id, 
		@Valid @RequestBody Student student) {		
		
		StudentDTO std = this.studentService.getStudentByID(id);				
		if (std == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		std = Converter.fromStudentToStudentDTO(student);
		
		std = this.studentService.updateStudent(std);
		logger.debug(" --> " + " student " + std.toString() + " has been updated." );
		
		student = Converter.fromStudentDTOToStudent(std);

	    return new ResponseEntity<>(student, HttpStatus.ACCEPTED);		
	}	
	
	@Override
	public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") Long id) {	
	    Boolean result = this.studentService.deleteStudent(id);
        
        if (!result) {
        	logger.debug(" --> " + " student: " + id + " is NOT FOUNT." );
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.debug(" --> " + " student: " + id + " has been deleted." );
        
        return new ResponseEntity<>(HttpStatus.OK);
	}	
	
	/**
	 * 
	 * @author FunnyGorilla
	 *
	 */
	private static class Converter {
		
		public static Student fromStudentDTOToStudent (StudentDTO cutomerDTO) {
			Student student = new Student();
			// simple converter logic.
		    BeanUtils.copyProperties(cutomerDTO, student);
		    return student;
	    }
		
		public static StudentNumber fromStudentDTOToStudentNumber (StudentDTO studentDTO) {
			StudentNumber customer = new StudentNumber();
			// simple converter logic.
			customer.setId(studentDTO.getId());
		    return customer;
	    }

	    public static StudentDTO fromStudentToStudentDTO(Student student) {
			StudentDTO studentDTO = new StudentDTO();
			// simple converter logic.
		    BeanUtils.copyProperties(student, studentDTO);
		    return studentDTO;
	    }
		
	}

}
