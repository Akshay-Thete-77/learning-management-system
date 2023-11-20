
package com.te.lms.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.te.lms.enums.EmployeeStatus;

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
@Table(name = "employee_primary")
public class Employee {

	@Id
	private String employeeId;

	@Column(name = "employee_name")
	private String employeeName;

	@Column(name = "date_of_joining")
	private Date dateOfJoining;

	@Column(name = "date_of_birth")
	private Date dateOfBirth;

	@Column(name = "email")
	private String employeeEmail;

	@Column(name = "blood_group")
	private String employeeBloodGroup;

	@Column(name = "designation")
	private String employeeDesignation;

	@Column(name = "gender")
	private String employeeGender;

	@Column(name = "nationality")
	private String employeeNationality;

	@Column(name = "status")
	private EmployeeStatus employeeStatus;

	private String reasonForStatusChange;

	@OneToOne(cascade = CascadeType.ALL)
	private EmployeeSecondary employeeSecondary;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
	private List<EmployeeEducation> employeeEducations;

	@ManyToMany(cascade = CascadeType.ALL)
	private List<EmployeeAddress> employeeAddresses;

	@OneToOne(cascade = CascadeType.ALL)
	private EmployeeBank employeeBank;

	@OneToOne(cascade = CascadeType.ALL)
	private EmployeeContact employeeContact;

	@ManyToMany(cascade = CascadeType.ALL)
	private List<EmployeeSkill> employeeSkills;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
	private List<EmployeeExperience> employeeExperiences;

	@ManyToOne(cascade = CascadeType.ALL)
	private Batch batch;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
	private List<MockDetail> mockDetails;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
	private List<EmployeeAttendance> employeeAttendances;
}
