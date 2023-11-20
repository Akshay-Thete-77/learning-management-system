package com.te.lms.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.te.lms.dto.SkillDTO;
import com.te.lms.entity.EmployeeSkill;
@Component
public class SkillUtils {
	public SkillDTO entityToDTO(EmployeeSkill employeeSkill) {
		SkillDTO skillDTO = new SkillDTO();
		BeanUtils.copyProperties(employeeSkill, skillDTO);
		return skillDTO;
	}

	public EmployeeSkill dtoToEntity(SkillDTO skillDTO) {
		EmployeeSkill employeeSkill = new EmployeeSkill();
		BeanUtils.copyProperties(skillDTO, employeeSkill);
		return employeeSkill;
	}
}
