package com.te.lms.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

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
public class ContactDTO {
	private String employeeId;
	private String employeeContactNo;
	private String contactType;
}
