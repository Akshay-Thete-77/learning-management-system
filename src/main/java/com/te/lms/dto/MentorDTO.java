package com.te.lms.dto;

import java.util.List;

import com.te.lms.entity.EmployeeSkill;

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
public class MentorDTO {
	private String employeeId;
	private String mentorName;
	private String emailId;
	private List<TechnologyDTO> employeeSkills;
	private String password;

}
