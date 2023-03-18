package com.cst438.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cst438.domain.Student;
import com.cst438.domain.StudentDTO;
import com.cst438.domain.StudentRepository;

@RestController
public class StudentController {

	
	@Autowired
	StudentRepository studentRepository;
	
	
	
	
	@PostMapping("/student") 
	public String createNewStudent(){
		return "";
	}
	
	
	@PutMapping("/student/{studId}") 
	@Transactional
	public void updateHold (@PathVariable("studId") Integer studentId, @RequestBody StudentDTO student) {
		Student stud = studentRepository.findById(studentId).orElse(null);

		// Check if there exists a student to change Hold
		if (stud == null) {
			throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "Invalid student primary key. "+ studentId);
		}
		
		//Eliminate Hold status if Hold Id is released
		if (student.statusId == 0) {
			stud.setStatus(null);			
		}
		
		//Alter Hold status and Hold id for student
		else { stud.setStatus(student.status);}
		stud.setStatusCode(student.statusId);
		studentRepository.save(stud);
	}
	
}
