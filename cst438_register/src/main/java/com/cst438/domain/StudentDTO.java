package com.cst438.domain;

//import java.util.List;

public class StudentDTO {

		public int id;
		public String student_email;
		public String student_name;
		public String status;
		public int statusId;
	
	// /*
	public StudentDTO(){
		this.id = 0;
		this.student_email=null;
		this.student_name=null;
		this.status=null;
		this.statusId=0;
	}
	
	public StudentDTO(String name, String email){
		this.id = 0;
		this.student_email=email;
		this.student_name=name;
		this.status=null;
		this.statusId=0;
	}
	///// */
	
	@Override
	public String toString() {
		return "Student DTO [id=" + id + ", student_name=" + student_name + ", student_email=" + student_email + 
				", status=" + status + ", statusId=" + statusId + "]";
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