package com.te.lms.dto;

import java.util.Date;
import java.util.List;

import com.te.lms.entity.Employee;
import com.te.lms.entity.EmployeeAddress;
import com.te.lms.entity.EmployeeBank;
import com.te.lms.entity.EmployeeEducation;
import com.te.lms.entity.EmployeeSecondary;

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
public class SecondaryDTO {

	private String employeeId;
	private String employeePanNo;
	private long employeeAadhar;
	private String employeeFather;
	private String employeeMother;
	private String employeeSpouse;
	private String employeePassport;
	private String employeeMaritalStatus;
}
