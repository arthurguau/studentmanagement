package com.funnygorilla.studentmanagement.services;  

import java.util.List;

import com.funnygorilla.studentmanagement.dto.StudentDTO;

public interface StudentService {  
	/**
	 * 
	 * @return
	 */
	public List<StudentDTO> getAllStudents();
	/**
	 * 
	 * @param id
	 * @return
	 */
	public StudentDTO getStudentByID(Long id);	
	/**
	 * 
	 * @param student
	 * @return
	 */
	public Long createStudent(StudentDTO student);
	/**
	 * 
	 * @param student
	 * @return
	 */
	public StudentDTO updateStudent (StudentDTO student );
	/**
	 * 
	 * @param studentId
	 * @return
	 */
	public boolean deleteStudent (Long studentId) ;
}  