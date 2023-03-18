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

//import com.cst438.domain.AssignmentGradeRepository;
import com.cst438.domain.Student;
//import com.cst438.domain.GradebookDTO;
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
		//return "";
		Student stud = studentRepository.findById(studentId).orElse(null);
		//StudentDTO tempstud = new StudentDTO(student.id, student.statusId);
		//student.getStatusId;
		if (stud == null) {
			throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "Invalid student primary key. "+ studentId);
		}
		if (student.statusId > 2) {
			throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "Invalid status code.");
		}
		System.out.printf("%s\n", stud.toString());
		stud.setStatusCode(student.statusId);
		studentRepository.save(stud);
		System.out.print(student.statusId);
		stud.toString();
		//studentRepository.save(stud);
	}
	
}
