package com.te.lms.service.impl;

import static com.te.lms.constants.EmployeeConstants.EMPLOYEE_ID_NOT_FOUND;
import static com.te.lms.enums.RequestStatus.PENDING;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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
import com.te.lms.entity.Employee;
import com.te.lms.entity.EmployeeAddress;
import com.te.lms.entity.EmployeeAttendance;
import com.te.lms.entity.EmployeeBank;
import com.te.lms.entity.EmployeeContact;
import com.te.lms.entity.EmployeeEducation;
import com.te.lms.entity.EmployeeExperience;
import com.te.lms.entity.EmployeeRequest;
import com.te.lms.entity.EmployeeSecondary;
import com.te.lms.entity.EmployeeSkill;
import com.te.lms.entity.MockDetail;
import com.te.lms.enums.EmployeeStatus;
import com.te.lms.exception.EmployeeNotFoundException;
import com.te.lms.repository.AttendanceRepository;
import com.te.lms.repository.ContactRepository;
import com.te.lms.repository.EmployeeEducationRepository;
import com.te.lms.repository.EmployeeRepository;
import com.te.lms.repository.MockDetailRepository;
import com.te.lms.repository.RequestRepository;
import com.te.lms.response.EmployeeExperienceRepository;
import com.te.lms.service.EmployeeService;
import com.te.lms.utils.AddressUtils;
import com.te.lms.utils.AttendenceUtils;
import com.te.lms.utils.BankUtils;
import com.te.lms.utils.ContactUtils;
import com.te.lms.utils.EducationUtils;
import com.te.lms.utils.EmployeeUtils;
import com.te.lms.utils.ExperienceUtils;
import com.te.lms.utils.MockDetailsUtils;
import com.te.lms.utils.SecondaryUtils;
import com.te.lms.utils.SkillUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;
	private final RequestRepository requestRepRepository;
	private final MockDetailRepository mockDetailRepository;
	private final MockDetailsUtils mockDetailsUtils;
	private final AttendenceUtils attendenceUtils;
	private final AttendanceRepository attendanceRepository;
	private final EmployeeUtils employeeUtils;
	private final SecondaryUtils secondaryUtils;
	private final EducationUtils educationUtils;
	private final AddressUtils addressUtils;
	private final BankUtils bankUtils;
	private final SkillUtils skillUtils;
	private final ExperienceUtils experienceUtils;
	private final ContactUtils contactUtils;
	private final EmployeeEducationRepository employeeEducationRepository;
	private final ContactRepository ContactRepository;

	@Override
	public Optional<String> registerEmployee(EmployeeDTO employeeDTO) {
		Employee employee = new Employee();
		Optional<Employee> employeeOp = employeeRepository.findById(employeeDTO.getEmployeeId());
		if (employeeOp.isPresent()) {
			employee = employeeOp.get();
		}
		BeanUtils.copyProperties(employeeDTO, employee);
		return Optional.ofNullable(employeeRepository.save(employee).getEmployeeId());

	}

	@Override
	public Optional<String> updateSecondary(SecondaryDTO secondaryDTO) {
		EmployeeSecondary employeeSecondary = new EmployeeSecondary();
		BeanUtils.copyProperties(secondaryDTO, employeeSecondary);

		Employee employee = employeeRepository.findById(secondaryDTO.getEmployeeId())
				.orElseThrow(() -> new EmployeeNotFoundException(secondaryDTO.getEmployeeId()));

		employee.setEmployeeSecondary(employeeSecondary);
		return Optional.ofNullable(employeeRepository.save(employee).getEmployeeId());
	}

	@Override
	public Optional<String> updateEducation(EducationDTO educationDTO) {
		String educationId = educationDTO.getEmployeeId() + educationDTO.getEducationType();
		EmployeeEducation employeeEducation = employeeEducationRepository.findById(educationId)
				.orElse(new EmployeeEducation());
		BeanUtils.copyProperties(educationDTO, employeeEducation);
		employeeEducation.setEducationId(educationId);

		Employee employee = employeeRepository.findById(educationDTO.getEmployeeId())
				.orElseThrow(() -> new EmployeeNotFoundException(educationDTO.getEmployeeId()));

		employee.getEmployeeEducations().add(employeeEducation);
		employeeEducation.setEmployee(employee);
		return Optional.ofNullable(employeeRepository.save(employee).getEmployeeId());
	}

	@Override
	public Optional<String> updateBank(BankDTO bankDTO) {
		EmployeeBank employeeBank = new EmployeeBank();
		BeanUtils.copyProperties(bankDTO, employeeBank);

		Employee employee = employeeRepository.findById(bankDTO.getEmployeeId())
				.orElseThrow(() -> new EmployeeNotFoundException(bankDTO.getEmployeeId()));

		employee.setEmployeeBank(employeeBank);
		return Optional.ofNullable(employeeRepository.save(employee).getEmployeeId());
	}

	@Override
	public Optional<String> updateAddress(AddressDTO addressDTO) {
		EmployeeAddress employeeAddress = new EmployeeAddress();
		BeanUtils.copyProperties(addressDTO, employeeAddress);

		Employee employee = employeeRepository.findById(addressDTO.getEmployeeId())
				.orElseThrow(() -> new EmployeeNotFoundException(addressDTO.getEmployeeId()));

		employee.getEmployeeAddresses().add(employeeAddress);
		return Optional.ofNullable(employeeRepository.save(employee).getEmployeeId());
	}

	@Override
	public Optional<String> updateSkill(SkillDTO skillDTO) {
		EmployeeSkill employeeSkill = new EmployeeSkill();
		BeanUtils.copyProperties(skillDTO, employeeSkill);

		Employee employee = employeeRepository.findById(skillDTO.getEmployeeId())
				.orElseThrow(() -> new EmployeeNotFoundException(skillDTO.getEmployeeId()));

		employee.getEmployeeSkills().add(employeeSkill);
		return Optional.ofNullable(employeeRepository.save(employee).getEmployeeId());
	}

	@Override
	public Optional<String> updateExperience(ExperienceDTO experienceDTO) {
		EmployeeExperience employeeExperience = new EmployeeExperience();
		BeanUtils.copyProperties(experienceDTO, employeeExperience);

		Employee employee = employeeRepository.findById(experienceDTO.getEmployeeId())
				.orElseThrow(() -> new EmployeeNotFoundException(EMPLOYEE_ID_NOT_FOUND));

		employee.getEmployeeExperiences().add(employeeExperience);
		employeeExperience.setEmployee(employee);
		return Optional.ofNullable(employeeRepository.save(employee).getEmployeeId());
	}

	@Override
	public Optional<String> updateContact(ContactDTO contactDTO) {
		EmployeeContact employeeContact = ContactRepository.findById(contactDTO.getEmployeeContactNo())
				.orElse(new EmployeeContact());
		BeanUtils.copyProperties(contactDTO, employeeContact);

		Employee employee = employeeRepository.findById(contactDTO.getEmployeeId())
				.orElseThrow(() -> new EmployeeNotFoundException(contactDTO.getEmployeeId()));
		employee.setEmployeeContact(employeeContact);

		List<EmployeeEducation> employeeEducations = employee.getEmployeeEducations();
		List<EmployeeExperience> employeeExperiences = employee.getEmployeeExperiences();

		EmployeeRequest employeeRequest = new EmployeeRequest();
		employeeRequest.setRequestId(employee.getEmployeeId());
		employeeRequest.setEmployee(employee);
		employeeRequest.setRequestStatus(PENDING);
		employeeRequest.setEmployeeContactNo(employee.getEmployeeContact().getEmployeeContactNo());
		employeeRequest.setPercentage(employeeEducations.get(employeeEducations.size() - 1).getPercentage());

		employeeRequest.setYearOfPassing(employeeEducations.get(employeeEducations.size() - 1).getYearOfPassing());
		employeeRequest
				.setYearOfExperience(employeeExperiences.get(employeeExperiences.size() - 1).getYearOfExperience());

		requestRepRepository.save(employeeRequest);
		return Optional.ofNullable(employeeRepository.save(employee).getEmployeeId());
	}

	@Override
	public List<MockDetailDTO> getMockRatings(String employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new EntityNotFoundException(employeeId));
		return employee.getMockDetails().stream().map(m -> mockDetailsUtils.entityToDTO(m))
				.collect(Collectors.toList());
	}

	@Override
	public EmployeeAttendanceDTO getEmployeeAttendance(String employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new EntityNotFoundException(employeeId));
		EmployeeAttendance employeeAttendance = employee.getEmployeeAttendances()
				.get(employee.getEmployeeAttendances().size() - 1);
		EmployeeAttendanceDTO employeeAttendanceDTO = attendenceUtils.entityToDTO(employeeAttendance);
		return employeeAttendanceDTO;
	}

	@Override
	public Optional<String> updateMockRatings(MockDetailDTO mockDetailDTO) {
		Employee employee = employeeRepository.findById(mockDetailDTO.getEmployeeId())
				.orElseThrow(() -> new EntityNotFoundException(mockDetailDTO.getEmployeeId()));
		MockDetail mockDetail = mockDetailsUtils.dtoToEntity(mockDetailDTO);
		if (employee.getBatch().getMocks() != null && employee.getBatch().getMocks().size() > 0) {
			mockDetail.setMockTaken(
					employee.getBatch().getMocks().get(employee.getBatch().getMocks().size() - 1).getMockNo());
		}
		employee.getMockDetails().add(mockDetail);
		mockDetail.setEmployee(employee);
		mockDetail.setMock(employee.getBatch().getMocks().get(employee.getBatch().getMocks().size() - 1));
		mockDetailRepository.save(mockDetail);
		return Optional.ofNullable(employeeRepository.save(employee).getEmployeeId());
	}

	@Override
	public Optional<String> setAttendance(EmployeeAttendanceDTO employeeAttendanceDTO) {
		Employee employee = employeeRepository.findById(employeeAttendanceDTO.getEmployeeId())
				.orElseThrow(() -> new EntityNotFoundException(employeeAttendanceDTO.getEmployeeId()));
		EmployeeAttendance employeeAttendance = attendenceUtils.dtoToEntity(employeeAttendanceDTO);
		Optional<EmployeeAttendance> attendenceFromDB = attendanceRepository
				.findById(employeeAttendance.getAttendenceId());
		if (employeeAttendance.isPresent()) {
			if (employee.getEmployeeAttendances() != null && employee.getEmployeeAttendances().size() > 0) {
				employeeAttendance.setPresentDaysCount(employee.getEmployeeAttendances()
						.get(employee.getEmployeeAttendances().size() - 1).getPresentDaysCount() + 1);
				employeeAttendance.setAbsentDaysCount(employee.getEmployeeAttendances()
						.get(employee.getEmployeeAttendances().size() - 1).getAbsentDaysCount());
			} else {
				employeeAttendance.setPresentDaysCount(1);
				employeeAttendance.setAbsentDaysCount(0);
			}
		} else {
			if (employee.getEmployeeAttendances() != null && employee.getEmployeeAttendances().size() > 0) {
				employeeAttendance.setPresentDaysCount(employee.getEmployeeAttendances()
						.get(employee.getEmployeeAttendances().size() - 1).getPresentDaysCount());
				employeeAttendance.setAbsentDaysCount(employee.getEmployeeAttendances()
						.get(employee.getEmployeeAttendances().size() - 1).getAbsentDaysCount() + 1);
			} else {
				employeeAttendance.setPresentDaysCount(0);
				employeeAttendance.setAbsentDaysCount(1);
			}
		}
		employeeAttendance.setEmployee(employee);
		attendanceRepository.save(employeeAttendance);
		return Optional.ofNullable(employeeRepository.save(employee).getEmployeeId());
	}

	@Override
	public Optional<String> updateStatus(StatusDTO statusDTO) {
		Employee employee = employeeRepository.findById(statusDTO.getEmployeeId())
				.orElseThrow(() -> new EntityNotFoundException(statusDTO.getEmployeeId()));
		if (statusDTO.getEmployeeStatus().toString().equalsIgnoreCase(EmployeeStatus.ABSCONDE.toString()))
			employee.setEmployeeStatus(EmployeeStatus.ABSCONDE);
		else if (statusDTO.getEmployeeStatus().toString().equalsIgnoreCase(EmployeeStatus.TERMINATED.toString()))
			employee.setEmployeeStatus(EmployeeStatus.TERMINATED);
		else
			employee.setEmployeeStatus(EmployeeStatus.ACTIVE);
		employee.setReasonForStatusChange(statusDTO.getReasonForStatusChange());
		return Optional.ofNullable(employeeRepository.save(employee).getEmployeeId());
	}

	@Override
	public Optional<String> getEmployeeDetails(String employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new EntityNotFoundException(employeeId));
		GroupDTOs groupDTOs = new GroupDTOs();
		groupDTOs.setEmployee(employeeUtils.entityToDTO(employee));
		groupDTOs.setSecondary(secondaryUtils.entityToDTO(employee.getEmployeeSecondary()));
		groupDTOs.setEducations(employee.getEmployeeEducations().stream().map(edu -> educationUtils.entityToDTO(edu))
				.collect(Collectors.toList()));
		groupDTOs.setAddresses(employee.getEmployeeAddresses().stream().map(a -> addressUtils.entityToDTO(a))
				.collect(Collectors.toList()));
		groupDTOs.setBank(bankUtils.entityToDTO(employee.getEmployeeBank()));
		groupDTOs.setSkills(
				employee.getEmployeeSkills().stream().map(s -> skillUtils.entityToDTO(s)).collect(Collectors.toList()));
		groupDTOs.setExperiences(employee.getEmployeeExperiences().stream().map(ex -> experienceUtils.entityToDTO(ex))
				.collect(Collectors.toList()));
		groupDTOs.setContact(contactUtils.entityToDTO(employee.getEmployeeContact()));
		return Optional.ofNullable("employee data with id " + employeeId + " provided");
	}

	@Override
	public Optional<String> updateEmployeeDetails(GroupDTOs groupDTOs) {
		Employee employee = employeeRepository.findById(groupDTOs.getEmployee().getEmployeeId())
				.orElseThrow(() -> new EmployeeNotFoundException(groupDTOs.getEmployee().getEmployeeId()));
		Employee employeeFromDTO = employeeUtils.dtoToEntity(groupDTOs.getEmployee());
		BeanUtils.copyProperties(employeeFromDTO, employee);
		employee.setEmployeeSecondary(secondaryUtils.dtoToEntity(groupDTOs.getSecondary()));
		employee.setEmployeeEducations(groupDTOs.getEducations().stream().map(e -> educationUtils.dtoToEntity(e))
				.collect(Collectors.toList()));

		employee.setEmployeeAddresses(
				groupDTOs.getAddresses().stream().map(a -> addressUtils.dtoToEntity(a)).collect(Collectors.toList()));

		employee.setEmployeeBank(bankUtils.dtoToEntity(groupDTOs.getBank()));
		employee.setEmployeeSkills(
				groupDTOs.getSkills().stream().map(s -> skillUtils.dtoToEntity(s)).collect(Collectors.toList()));
		employee.setEmployeeExperiences(groupDTOs.getExperiences().stream().map(ex -> experienceUtils.dtoToEntity(ex))
				.collect(Collectors.toList()));
		employee.setEmployeeContact(contactUtils.dtoToEntity(groupDTOs.getContact()));
		return Optional.ofNullable(employeeRepository.save(employee).getEmployeeId());
	}

}
