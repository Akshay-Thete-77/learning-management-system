package com.te.lms.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.te.lms.dto.SecondaryDTO;
import com.te.lms.entity.EmployeeSecondary;
@Component
public class SecondaryUtils {

	public SecondaryDTO entityToDTO(EmployeeSecondary employeeSecondary) {
		SecondaryDTO secondaryDTO = new SecondaryDTO();
		BeanUtils.copyProperties(employeeSecondary, secondaryDTO);
		return secondaryDTO;
	}

	public EmployeeSecondary dtoToEntity(SecondaryDTO secondaryDTO) {
		EmployeeSecondary employeeSecondary = new EmployeeSecondary();
		BeanUtils.copyProperties(secondaryDTO, employeeSecondary);
		return employeeSecondary;
	}
}
