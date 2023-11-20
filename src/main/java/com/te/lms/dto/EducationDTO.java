package com.te.lms.dto;

import com.te.lms.enums.EducationType;
import com.te.lms.enums.Specialization;

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
public class EducationDTO {

	private String employeeId;
	private EducationType educationType;
	private int yearOfPassing;
	private double percentage;
	private String universityName;
	private String instituteName;
	private Specialization specialization;
	private String state;
}
