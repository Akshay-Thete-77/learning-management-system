package com.te.lms.dto;

import java.util.List;

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
public class GroupDTOs {
	private EmployeeDTO employee;
	private SecondaryDTO secondary;
	private List<EducationDTO> educations;
	private List<AddressDTO> addresses;
	private BankDTO bank;
	private List<SkillDTO> skills;
	private List<ExperienceDTO> experiences;
	private ContactDTO contact;

}
