package com.te.lms.controller;

import static com.te.lms.constants.EmployeeConstants.EMPLOYEE_ADDRESS_UPDATED;
import static com.te.lms.constants.EmployeeConstants.EMPLOYEE_BANK_DETAILS_UPDATED;
import static com.te.lms.constants.EmployeeConstants.EMPLOYEE_CONTACT_UPDATED;
import static com.te.lms.constants.EmployeeConstants.EMPLOYEE_DETAILS_UPDATED;
import static com.te.lms.constants.EmployeeConstants.EMPLOYEE_EDUCATION_UPDATED;
import static com.te.lms.constants.EmployeeConstants.EMPLOYEE_EXPERIENCE_UPDATED;
import static com.te.lms.constants.EmployeeConstants.EMPLOYEE_REGISTERED;
import static com.te.lms.constants.EmployeeConstants.EMPLOYEE_SECONDARY_UPDATED;
import static com.te.lms.constants.EmployeeConstants.EMPLOYEE_SKILL_UPDATED;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.lms.dto.AddressDTO;
import com.te.lms.dto.BankDTO;
import com.te.lms.dto.ContactDTO;
import com.te.lms.dto.EducationDTO;
import com.te.lms.dto.EmployeeDTO;
import com.te.lms.dto.ExperienceDTO;
import com.te.lms.dto.GroupDTOs;
import com.te.lms.dto.LoginDto;
import com.te.lms.dto.SecondaryDTO;
import com.te.lms.dto.SkillDTO;
import com.te.lms.exception.EmployeeNotFoundException;
import com.te.lms.response.SuccessResponse;
import com.te.lms.service.EmployeeService;
import com.te.lms.utils.JwtUtils;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/public")
@RequiredArgsConstructor
public class ApplicationController {

	private final EmployeeService employeeService;
	private final JwtUtils jwtUtils;
	private final AuthenticationManager authenticationManager;

	@PutMapping("/register/employee")
	public ResponseEntity<SuccessResponse> registerEmployee(@RequestBody EmployeeDTO employeeDTO) {
		Optional<String> employeeId = employeeService.registerEmployee(employeeDTO);
		return ResponseEntity.<SuccessResponse>ok(SuccessResponse.builder().data(employeeId)
				.message(EMPLOYEE_REGISTERED).timestamp(LocalDateTime.now()).build());

	}

	@PutMapping("update/secondary")
	public ResponseEntity<SuccessResponse> updateSecondary(@RequestBody SecondaryDTO secondaryDTO) {
		String employeeId = employeeService.updateSecondary(secondaryDTO)
				.orElseThrow(() -> new EmployeeNotFoundException(secondaryDTO.getEmployeeId()));
		return ResponseEntity.<SuccessResponse>ok().body(SuccessResponse.builder().data(employeeId)
				.message(EMPLOYEE_SECONDARY_UPDATED).timestamp(LocalDateTime.now()).build());
	}

	@PutMapping("/update/education")
	public ResponseEntity<SuccessResponse> updateEducation(@RequestBody EducationDTO educationDTO) {
		String employeeId = employeeService.updateEducation(educationDTO)
				.orElseThrow(() -> new EmployeeNotFoundException(educationDTO.getEmployeeId()));
		return ResponseEntity.<SuccessResponse>ok().body(SuccessResponse.builder().data(employeeId)
				.message(EMPLOYEE_EDUCATION_UPDATED).timestamp(LocalDateTime.now()).build());
	}

	@PutMapping("/update/bank")
	public ResponseEntity<SuccessResponse> updateBank(@RequestBody BankDTO bankDTO) {
		String employeeId = employeeService.updateBank(bankDTO)
				.orElseThrow(() -> new EmployeeNotFoundException(bankDTO.getEmployeeId()));
		return ResponseEntity.<SuccessResponse>ok().body(SuccessResponse.builder().data(employeeId)
				.message(EMPLOYEE_BANK_DETAILS_UPDATED).timestamp(LocalDateTime.now()).build());
	}

	@PutMapping("/update/address")
	public ResponseEntity<SuccessResponse> updateAddress(@RequestBody AddressDTO addressDTO) {
		String employeeId = employeeService.updateAddress(addressDTO)
				.orElseThrow(() -> new EmployeeNotFoundException(addressDTO.getEmployeeId()));
		return ResponseEntity.<SuccessResponse>ok().body(SuccessResponse.builder().data(employeeId)
				.message(EMPLOYEE_ADDRESS_UPDATED).timestamp(LocalDateTime.now()).build());
	}

	@PutMapping("/update/skill")
	public ResponseEntity<SuccessResponse> updateSkill(@RequestBody SkillDTO skillDTO) {
		String employeeId = employeeService.updateSkill(skillDTO)
				.orElseThrow(() -> new EmployeeNotFoundException(skillDTO.getEmployeeId()));
		return ResponseEntity.<SuccessResponse>ok().body(SuccessResponse.builder().data(employeeId)
				.message(EMPLOYEE_SKILL_UPDATED).timestamp(LocalDateTime.now()).build());
	}

	@PutMapping("/update/experience")
	public ResponseEntity<SuccessResponse> updateExperience(@RequestBody ExperienceDTO experienceDTO) {
		String employeeId = employeeService.updateExperience(experienceDTO)
				.orElseThrow(() -> new EmployeeNotFoundException(experienceDTO.getEmployeeId()));
		return ResponseEntity.<SuccessResponse>ok().body(SuccessResponse.builder().data(employeeId)
				.message(EMPLOYEE_EXPERIENCE_UPDATED).timestamp(LocalDateTime.now()).build());
	}

	@PutMapping("/update/contact")
	public ResponseEntity<SuccessResponse> updateContact(@RequestBody ContactDTO contactDTO) {
		String employeeId = employeeService.updateContact(contactDTO)
				.orElseThrow(() -> new EmployeeNotFoundException(contactDTO.getEmployeeId()));
		return ResponseEntity.<SuccessResponse>ok().body(SuccessResponse.builder().data(employeeId)
				.message(EMPLOYEE_CONTACT_UPDATED).timestamp(LocalDateTime.now()).build());
	}

	@PostMapping("/employee/details/{employeeId}")
	public ResponseEntity<SuccessResponse> getEmployeeDetails(@PathVariable String employeeId) {
		Optional<String> message = employeeService.getEmployeeDetails(employeeId);
		return ResponseEntity.<SuccessResponse>ok().body(SuccessResponse.builder().data(message)
				.message(EMPLOYEE_CONTACT_UPDATED).timestamp(LocalDateTime.now()).build());
	}

	@PutMapping("update/employee/details")
	public ResponseEntity<SuccessResponse> updateEmployeeDetails(@RequestBody GroupDTOs groupDTOs) {
		Optional<String> employeeId = employeeService.updateEmployeeDetails(groupDTOs);
		return ResponseEntity.<SuccessResponse>ok().body(SuccessResponse.builder().data(employeeId)
				.message(EMPLOYEE_DETAILS_UPDATED).timestamp(LocalDateTime.now()).build());
	}

	@PostMapping(path = "/login")
	public ResponseEntity<SuccessResponse> login(@RequestBody LoginDto loginDto) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
		String token = jwtUtils.generateToken(loginDto.getUsername());
		return ResponseEntity.<SuccessResponse>ok().body(SuccessResponse.builder().token(token)
				.timestamp(LocalDateTime.now()).message("Login successfull!").build());
	}

}
