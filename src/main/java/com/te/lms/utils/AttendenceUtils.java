package com.te.lms.utils;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.te.lms.dto.EmployeeAttendanceDTO;
import com.te.lms.entity.EmployeeAttendance;

@Component
public class AttendenceUtils {
	public EmployeeAttendanceDTO entityToDTO(EmployeeAttendance employeeAttendance) {
		EmployeeAttendanceDTO employeeAttendanceDTO = new EmployeeAttendanceDTO();
		BeanUtils.copyProperties(employeeAttendance, employeeAttendanceDTO);
		employeeAttendanceDTO.setEmployeeId(employeeAttendance.getEmployee().getEmployeeId());
		return employeeAttendanceDTO;
	}

	public EmployeeAttendance dtoToEntity(EmployeeAttendanceDTO employeeAttendanceDTO) {
		EmployeeAttendance employeeAttendance = new EmployeeAttendance();
		BeanUtils.copyProperties(employeeAttendanceDTO, employeeAttendance);
		if (employeeAttendanceDTO.getDate() != null)
			employeeAttendance.setAttendenceId(employeeAttendanceDTO.getEmployeeId() + employeeAttendanceDTO.getDate());
		else {
			employeeAttendance.setAttendenceId(employeeAttendanceDTO.getEmployeeId() + LocalDate.now());
			employeeAttendance.setDate(Date.valueOf(LocalDate.now()));
		}
		return employeeAttendance;
	}
}
