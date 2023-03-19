package com.cst438.controller;

// Packages from springframework
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

// Packages from other files
import com.cst438.domain.Student;
import com.cst438.domain.StudentDTO;
import com.cst438.domain.StudentRepository;

@RestController
public class StudentController {

	
	@Autowired
	StudentRepository studentRepository;
	
	
	// Add new student by email and name
	@PostMapping("/student") 
	@Transactional
	public StudentDTO addStudent(@RequestBody StudentDTO studentDTO) { 
		
		// See if email already exists
		Student emailExist = studentRepository.findByEmail(studentDTO.student_email);
		Student student = new Student();
		if (emailExist == null) {
			
			// Input name an email into new student entity
			student.setEmail(studentDTO.student_email);
			student.setName(studentDTO.student_name);
			student.setStatus(null);
			student.setStatusCode(0);
		}	
		
		// Throw error if email already exists
		else { throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "Student email already in use. "+ studentDTO.student_email);
		}
		
		// Save student to database and create StudentDTO to return output
		studentRepository.save(student);
		StudentDTO studReturn = new StudentDTO(student.getName(), student.getEmail());
		return studReturn;
	}
	
	
	// Hold for student and released Hold by student Id Number
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
