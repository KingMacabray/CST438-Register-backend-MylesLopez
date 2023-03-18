package com.cst438.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cst438.domain.ScheduleDTO;
import com.cst438.domain.Student;
import com.cst438.domain.StudentDTO;
import com.cst438.domain.StudentRepository;

@RestController
public class StudentController {

	
	@Autowired
	StudentRepository studentRepository;
	
	
	
	
	@PostMapping("/student") 
	//public String createNewStudent(){
	@Transactional
	public StudentDTO addStudent(@RequestBody StudentDTO studentDTO) { 
		//return "";
		
		//Student emailExist = studentRepository.findByEmail(studentDTO.student_email).orElse(null);
		Student emailExist = studentRepository.findByEmail(studentDTO.student_email);
		Student student = new Student();
		if (emailExist == null) {
			student.setEmail(studentDTO.student_email);
			student.setName(studentDTO.student_name);
			student.setStatus(null);
			student.setStatusCode(0);
			//student.setEmail(studentDTO.student_email);
		}	
			
		else { throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "Student email already in use. "+ studentDTO.student_email);
		}
		
		//Student finishedStudent = studentRepository.save(student);
		studentRepository.save(student);
		//StudentDTO result = createStudentDTO(finishedStudent);
		StudentDTO studReturn = new StudentDTO(student.getName(), student.getEmail());
		//return student;
		
		
		return studReturn;
		
		
		
		/*Garbage to get rid of later /
		StudentDTO temp = studentDTO;
		return temp;
		/// */
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
