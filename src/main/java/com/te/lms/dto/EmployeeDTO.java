package com.te.lms.dto;

import java.util.Date;
import java.util.List;

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
public class EmployeeDTO {
	private String employeeId;
	private String employeeName;
	private Date dateOfJoining;
	private Date dateOfBirth;
	private String employeeEmail;
	private String employeeBloodGroup;
	private String employeeDesignation;
	private String employeeGender;
	private String employeeNationality;
	private String employeeStatus;
	private List<MockDetailDTO> mockDetails;
	private List<EmployeeAttendanceDTO> employeeAttendances;

}
