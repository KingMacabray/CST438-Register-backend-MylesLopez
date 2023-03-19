package com.cst438;

import static org.mockito.ArgumentMatchers.any;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

//import java.util.Calendar;
import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


import com.cst438.controller.StudentController;
/*
import com.cst438.controller.ScheduleController;
import com.cst438.domain.Course;
import com.cst438.domain.CourseRepository;
import com.cst438.domain.Enrollment;
import com.cst438.domain.EnrollmentRepository;
import com.cst438.domain.ScheduleDTO;
*/
import com.cst438.domain.StudentDTO;
import com.cst438.domain.Student;
import com.cst438.domain.StudentRepository;
import com.cst438.service.GradebookService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.test.context.ContextConfiguration;

//import org.junit.jupiter.api.extension.ExtendWith;

@ContextConfiguration(classes = { StudentController.class })
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest
public class JunitTestStudent {

	@MockBean
	StudentRepository studentRepository;
	
	
	@MockBean
	GradebookService gradebookService;
	
	@Autowired
    private MockMvc mvc;
	
	
	
	
	static final String URL = "http://localhost:8080";
	public static final int TEST_STUDENT_ID = 3;
	public static final String TEST_STUDENT_EMAIL = "test@csumb.edu";
	public static final String TEST_STUDENT_NAME  = "test";
	public static final int TEST_STATUS_CODE = 1;
	public static final String TEST_STATUS = "Hold from training";
	
	
	@Test
	public void addStudent() throws Exception {
		
		
		MockHttpServletResponse response;
		
		
		//Adding student and putting in information
		Student addedStudent = new Student();

		addedStudent.setEmail(TEST_STUDENT_EMAIL);
		addedStudent.setName(TEST_STUDENT_NAME);
		addedStudent.setStatusCode(TEST_STATUS_CODE);
		addedStudent.setStatus(TEST_STATUS);
		addedStudent.setStudent_id(TEST_STUDENT_ID);
		
		//*  List here is probably not needed but have in case
		List<Student> students = new java.util.ArrayList<>();
		students.add(addedStudent);
		//// */
		
		
		//Setting up for testing
		given(studentRepository.findById(TEST_STUDENT_ID)).willReturn(Optional.of(addedStudent));
        given(studentRepository.findByEmail(TEST_STUDENT_EMAIL)).willReturn(addedStudent);
        given(studentRepository.save(any(Student.class))).willReturn(addedStudent);
        
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.id = TEST_STUDENT_ID;
        studentDTO.statusId = TEST_STUDENT_ID;
        
        // Running through Mock building
        response = mvc.perform(
                MockMvcRequestBuilders
                  .post("/student")
                  .content(asJsonString(studentDTO))
                  .contentType(MediaType.APPLICATION_JSON)
                  .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
		
        
		// verify that return status = OK (value 200) 
		assertEquals(200, response.getStatus());
		
		// verify that returned data has non zero primary key
		StudentDTO result = fromJsonString(response.getContentAsString(), StudentDTO.class);
		assertNotEquals( 0  , result.id);
				
		// verify that repository save method was called.
		verify(studentRepository).save(any(Student.class));
		
		
	
	}
	
	
	// Classes for use in testing
	private static <T> T  fromJsonString(String str, Class<T> valueType ) {
		try {
			return new ObjectMapper().readValue(str, valueType);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private static String asJsonString(final Object obj) {
		try {

			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
}
