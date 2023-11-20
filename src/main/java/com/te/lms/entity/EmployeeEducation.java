package com.te.lms.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.te.lms.enums.EducationType;
import com.te.lms.enums.Specialization;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "employee_education")
public class EmployeeEducation {
	@Id
	private String educationId;

	@Column(name = "education_type")
	private EducationType educationType;

	@Column(name = "employee_name")
	private int yearOfPassing;

	@Column(name = "percentage")
	private double percentage;

	@Column(name = "university_name")
	private String universityName;

	@Column(name = "institute_name")
	private String instituteName;

	private Specialization specialization;

	private String state;

	@ManyToOne(cascade = CascadeType.ALL)
	private Employee employee;

}
