package com.funnygorilla.studentmanagement.services;  

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.funnygorilla.studentmanagement.dto.StudentDTO;
import com.funnygorilla.studentmanagement.repository.StudentRepository;
import com.funnygorilla.studentmanagement.services.domain.entity.StudentBO;

@Service  
public class StudentServiceImpl implements StudentService {  
	
	@Autowired  
	private StudentRepository studentRepository;  
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public List<StudentDTO> getAllStudents()
	{  
		List <StudentDTO> all = new ArrayList<StudentDTO>();  
		List <StudentBO> bos = this.studentRepository.getAllStudents();
		
		StudentDTO dto = null;
		Iterator<StudentBO> r = bos.iterator();
		while (r.hasNext()) {
			dto = Converter.fromStudentBOStudentDTO(r.next());
			all.add(dto);
		}
		return all;  
	}  
	
	@Override
	public StudentDTO getStudentByID(Long id)
	{  
		logger.debug(" ---------------> getStudentByID() called in Service. ");
		StudentBO studentBO = this.studentRepository.getStudentByID(id);
		StudentDTO dto = Converter.fromStudentBOStudentDTO(studentBO);
		return dto;
	}  
	
	@Override
	public Long createStudent(StudentDTO sdto){  
		logger.debug(" ---------------> [Student Service] createStudent() before save(). ");
		StudentBO sbo = Converter.fromStudentDTOToStudentBO(sdto);
		Long id = this.studentRepository.createStudent(sbo);
		logger.debug(" ---------------> [Student Service] createStudent() after save(). ");
		return id;
	}  
	
	@Override
	public StudentDTO updateStudent (StudentDTO stdo ) {
		StudentBO sbo = Converter.fromStudentDTOToStudentBO(stdo);
		sbo = this.studentRepository.updateStudent(sbo);
		StudentDTO sto = Converter.fromStudentBOStudentDTO(sbo);
		return  sto;
	}
	
	@Override
	public boolean deleteStudent (Long studentId) {
		return this.studentRepository.deleteStudent(studentId);
	}
	
	/**
	 * 
	 * @author guson
	 *
	 */
	private static class Converter {
		
		public static StudentBO fromStudentDTOToStudentBO (StudentDTO studentDTO) {
			StudentBO studentBO = new StudentBO();
			// simple converter logic.
		    BeanUtils.copyProperties(studentDTO, studentBO);
		    return studentBO;
	    }
		
	    public static StudentDTO fromStudentBOStudentDTO(StudentBO sbo) {
			StudentDTO studentDTO = new StudentDTO();
			// simple converter logic.
		    BeanUtils.copyProperties(sbo, studentDTO);
		    return studentDTO;
	    }
	}

}  