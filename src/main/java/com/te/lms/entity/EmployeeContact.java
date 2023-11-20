package com.te.lms.entity;

import java.util.Date;

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
@Table(name = "employee_contact")
public class EmployeeContact {
	@Id
	@Column(name = "contact_no")
	private String employeeContactNo;

	@Column(name = "contact_type")
	private String contactType;

	@OneToOne(mappedBy = "employeeContact")
	private Employee employee;

}
