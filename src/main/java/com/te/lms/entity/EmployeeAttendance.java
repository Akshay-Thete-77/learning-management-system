package com.te.lms.entity;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
public class EmployeeAttendance {
	@Id
	private String attendenceId;
	private Date date;
	private boolean present = false;
	private boolean absent = false;
	private int presentDaysCount;
	private int absentDaysCount;
	@ManyToOne(cascade = CascadeType.ALL)
	private Employee employee;

}
