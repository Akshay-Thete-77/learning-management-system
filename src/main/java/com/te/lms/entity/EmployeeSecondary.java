package com.te.lms.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
@Table(name = "employee_secondary")
public class EmployeeSecondary {

	@Id
	@Column(name = "pan_no")
	private String employeePanNo;

	@Column(name = "aadhar_no")
	private long employeeAadhar;

	@Column(name = "father_name")
	private String employeeFather;

	@Column(name = "mother_name")
	private String employeeMother;

	@Column(name = "spouse_name")
	private String employeeSpouse;

	@Column(name = "passport_no")
	private String employeePassport;

	@Column(name = "marital_status")
	private String employeeMaritalStatus;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "employeeSecondary")
	private Employee employee;

}
