package com.cst438.domain;

//import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends CrudRepository <Student, Integer> {
	
	// declare the following method to return a single Student object
	// default JPA behavior that findBy methods return List<Student> except for findById.
	@Query("select s from Student s where s.email=:student_email")
	Student findByEmail(@Param("student_email") String student_email);
	
	//Student findByEmail(String student_email);

}
