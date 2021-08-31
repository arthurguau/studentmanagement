package com.funnygorilla.studentmanagement.repository;  

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.funnygorilla.studentmanagement.repository.dataaccess.StudentDAO;
import com.funnygorilla.studentmanagement.repository.dataaccess.StudentDB;
import com.funnygorilla.studentmanagement.services.domain.entity.StudentBO;
 

@Repository
public class StudentRepository  
{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	StudentDAO studentDao;

	/**
	 * 
	 * @return
	 */
	public List<StudentBO> getAllStudents(){
		List<StudentDB> dbs = this.studentDao.findAll();
		
		if (dbs == null) return null;
		
		List <StudentBO> bos = new ArrayList<StudentBO> ();
		
		StudentBO bo = null;
		
		Iterator<StudentDB> r = dbs.iterator();
		while (r.hasNext()) {
			bo = new StudentBO();
			// simple data model transformation
			BeanUtils.copyProperties(r.next(), bo);
			bos.add(bo);
		}
		
		return bos;
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	public StudentBO getStudentByID(Long id) {
		StudentDB db = this.getStudentDB(id);
		
		// simple data model transformation from table entity to business object
		StudentBO bo = new StudentBO();
		BeanUtils.copyProperties(db, bo);
		
		return bo;
	}
	/**
	 * 
	 * @param student
	 * @return
	 */
	public Long createStudent(StudentBO student) {
		StudentDB db = new StudentDB();
		BeanUtils.copyProperties(student, db);
		db = this.studentDao.saveAndFlush(db);
		logger.debug("--> student " + db.toString() +  " created.");
		return db.getId();
	}
	/**
	 * 
	 * @param student
	 * @return
	 */
	public StudentBO updateStudent (StudentBO sbo ) {
		Long targetID = sbo.getId();
		StudentDB sdb = new StudentDB();
		
		// retrieve student from repository
		sdb = this.getStudentDB(targetID);
		
		// if it does not exist return null;
		if (sdb == null) {
			logger.warn("--> student " + targetID +  " does not exist!");
			return null;
		}
		
		// if it exist, update it and return updated record.
		BeanUtils.copyProperties(sbo, sdb);
        sdb = this.studentDao.saveAndFlush(sdb);
        
        StudentBO updatedSt = new StudentBO();
        BeanUtils.copyProperties(sdb, updatedSt);
	        
        logger.debug("--> student " + updatedSt.toString() +  " updated.");
		return updatedSt;
	}
	/**
	 * 
	 * @param studentId
	 */
	public boolean deleteStudent (Long studentId) {
		StudentDB sdb = this.getStudentDB(studentId);
		
		if (null == sdb) {
			logger.debug("--> student " + studentId +  " does not exit and nothing has been deleted.");
			return false;
		}
		
		this.studentDao.deleteById(studentId);
		logger.debug("--> student " + studentId +  " deleted.");
		return true;
	}
	/**
	 * retrieve student record by id.
	 * @param id
	 * @return
	 */
	private StudentDB getStudentDB (Long id) {
		
		StudentDB sdb = this.studentDao.findById(id).orElse(null);
		if (null == sdb) {
			logger.warn("--> student " + id +  " does not exist!");
		}
		
		return sdb;
	}

}