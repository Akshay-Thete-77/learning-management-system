package com.te.lms.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
@Table(name = "employee_experience")
public class EmployeeExperience {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "experience_seq")
	@SequenceGenerator(name = "experience_seq", allocationSize = 1, initialValue = 1)
	@Column(name = "experience_id")
	private Integer experienceId;

	@ManyToOne(cascade = CascadeType.ALL)
	private Employee employee;

	@Column(name = "company_name")
	private String companyName;

	@Column(name = "year_of_experience")
	private double yearOfExperience;

	@Column(name = "date_of_joining")
	private Date dateOfJoing;

	@Column(name = "date_of_relieving")
	private Date dateOfRelieving;

	private String designation;

	private String location;

}
