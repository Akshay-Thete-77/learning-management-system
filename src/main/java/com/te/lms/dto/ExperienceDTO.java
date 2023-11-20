package com.te.lms.dto;

import java.util.Date;

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
public class ExperienceDTO {

	private String employeeId;
	private String companyName;
	private double yearOfExperience;
	private Date dateOfJoing;
	private Date dateOfRelieving;
	private String designation;
	private String location;
}
