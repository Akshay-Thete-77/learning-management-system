package com.te.lms.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.te.lms.dto.ExperienceDTO;
import com.te.lms.entity.EmployeeExperience;
@Component
public class ExperienceUtils {
	public ExperienceDTO entityToDTO(EmployeeExperience employeeExperience) {
		ExperienceDTO experienceDTO = new ExperienceDTO();
		BeanUtils.copyProperties(employeeExperience, experienceDTO);
		return experienceDTO;

	}

	public EmployeeExperience dtoToEntity(ExperienceDTO experienceDTO) {
		EmployeeExperience employeeExperience = new EmployeeExperience();
		BeanUtils.copyProperties(experienceDTO, employeeExperience);
		return employeeExperience;
	}
}
