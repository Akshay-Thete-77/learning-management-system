package com.te.lms.service;

import java.util.List;
import java.util.Optional;

import com.te.lms.dto.AddressDTO;
import com.te.lms.dto.BankDTO;
import com.te.lms.dto.ContactDTO;
import com.te.lms.dto.EducationDTO;
import com.te.lms.dto.EmployeeAttendanceDTO;
import com.te.lms.dto.EmployeeDTO;
import com.te.lms.dto.ExperienceDTO;
import com.te.lms.dto.GroupDTOs;
import com.te.lms.dto.MockDetailDTO;
import com.te.lms.dto.SecondaryDTO;
import com.te.lms.dto.SkillDTO;
import com.te.lms.dto.StatusDTO;

public interface EmployeeService {

	public Optional<String> updateSecondary(SecondaryDTO secondaryDTO);

	public Optional<String> updateEducation(EducationDTO educationDTO);

	public Optional<String> updateBank(BankDTO bankDTO);

	public Optional<String> updateContact(ContactDTO contactDTO);

	public Optional<String> updateAddress(AddressDTO addressDTO);

	public Optional<String> updateSkill(SkillDTO skillDTO);

	public Optional<String> updateExperience(ExperienceDTO experienceDTO);

	public Optional<String> registerEmployee(EmployeeDTO employeeDTO);

	public List<MockDetailDTO> getMockRatings(String employeeId);

	public EmployeeAttendanceDTO getEmployeeAttendance(String employeeId);

	public Optional<String> updateMockRatings(MockDetailDTO mockDetailDTO);

	public Optional<String> setAttendance(EmployeeAttendanceDTO employeeAttendanceDTO);

	public Optional<String> updateStatus(StatusDTO statusDTO);

	public Optional<String> getEmployeeDetails(String employeeId);

	public Optional<String> updateEmployeeDetails(GroupDTOs groupDTOs);

}
