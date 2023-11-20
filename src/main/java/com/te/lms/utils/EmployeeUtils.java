package com.te.lms.utils;

import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.te.lms.dto.EmployeeDTO;
import com.te.lms.entity.Employee;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmployeeUtils {
	private final MockDetailsUtils mockDetailsUtils;
	private final AttendenceUtils attendenceUtils;

	public EmployeeDTO entityToDTO(Employee employee) {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		BeanUtils.copyProperties(employee, employeeDTO);
		if (employee.getMockDetails() != null) {
			employeeDTO.setMockDetails(employee.getMockDetails().stream().map(m -> mockDetailsUtils.entityToDTO(m))
					.collect(Collectors.toList()));
			;
		}
		if (employee.getEmployeeAttendances() != null) {
			employeeDTO.setEmployeeAttendances(employee.getEmployeeAttendances().stream()
					.map(a -> attendenceUtils.entityToDTO(a)).collect(Collectors.toList()));
		}
		return employeeDTO;
	}

	public Employee dtoToEntity(EmployeeDTO employeeDTO) {
		Employee employee = new Employee();
		BeanUtils.copyProperties(employeeDTO, employee);
		return employee;
	}
}
