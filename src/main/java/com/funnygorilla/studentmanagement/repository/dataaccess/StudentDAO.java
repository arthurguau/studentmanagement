package com.funnygorilla.studentmanagement.repository.dataaccess;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 * @author FunnyGorilla
 *
 */
public interface StudentDAO extends JpaRepository<StudentDB, Long> {
	
//	@Query("SELECT s FROM Student s WHERE s.name=(:name)")
//	List<StudentDB> findByLastName(@Param("name") String name);	

}
