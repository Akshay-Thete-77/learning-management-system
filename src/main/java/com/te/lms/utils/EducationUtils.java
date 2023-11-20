package com.te.lms.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.te.lms.dto.EducationDTO;
import com.te.lms.entity.EmployeeEducation;

@Component
public class EducationUtils {

	public EducationDTO entityToDTO(EmployeeEducation employeeEducation) {
		EducationDTO educationDTO = new EducationDTO();
		BeanUtils.copyProperties(employeeEducation, educationDTO);
		return educationDTO;
	}

	public EmployeeEducation dtoToEntity(EducationDTO educationDTO) {
		EmployeeEducation employeeEducation = new EmployeeEducation();
		BeanUtils.copyProperties(educationDTO, employeeEducation);
		String educationId = educationDTO.getEmployeeId() + educationDTO.getEducationType();
		employeeEducation.setEducationId(educationId);
		return employeeEducation;
	}
}
