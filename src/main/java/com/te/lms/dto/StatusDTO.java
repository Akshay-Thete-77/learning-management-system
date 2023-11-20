package com.te.lms.dto;

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
public class StatusDTO {
	private String employeeId;
	private EmployeeStatus employeeStatus;
	private String reasonForStatusChange;
}
