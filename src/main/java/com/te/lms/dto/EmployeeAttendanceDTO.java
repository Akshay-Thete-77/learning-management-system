package com.te.lms.dto;

import java.sql.Date;
import java.time.LocalDateTime;

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
public class EmployeeAttendanceDTO {
	private Date date;
	private boolean present = false;
	private boolean absent = false;
	private int presentDaysCount;
	private int absentDaysCount;
	private String employeeId;
}
