package com.cst438.domain;

//import java.util.List;

public class StudentDTO {

		public int id;
		public String student_email;
		public String student_name;
		public String status_name;
		public int statusId;
	
	/*
	public StudentDTO(){
		this.id = id;
		this.student_email=student_email;
		this.student_name=student_name;
		this.status_name=status_name;
		this.statusId=statusId;
	}
	/*
	public StudentDTO(int studentId, int status_ID){
		this.id = studentId;
		//this.student_email=student_email;
		//this.student_name=student_name;
		//this.status_name=status_name;
		this.statusId=status_ID;
	}
	*/
	
	@Override
	public String toString() {
		return "Student DTO [id=" + id + ", student_name=" + student_name + ", student_email=" + student_email + 
				", status_name=" + status_name + ", statusId=" + statusId + "]";
	}
		
//	}

//	public int course_id;
//	public List<GradeDTO> grades;
	
//	@Override
//	public String toString() {
//		return "StudentDTOG [course_id=" + course_id + ", grades=[" + grades + "] ]";
//	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentDTO other = (StudentDTO) obj;
//		if (course_id != other.course_id)
//			return false;
		if (id != other.id)
			return false;
		if (student_email == null) {
			if (other.student_email != null)
				return false;
		} else if (!student_email.equals(other.student_email))
			return false;
		if (student_name == null) {
			if (other.student_name != null)
				return false;
		} else if (!student_name.equals(other.student_name))
			return false;
		return true;
		
	}

}