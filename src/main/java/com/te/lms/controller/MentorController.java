package com.te.lms.controller;

import static com.te.lms.constants.AttendanceConstants.ATTENDANCE_UPDATED;
import static com.te.lms.constants.BatchConstants.BATCH_DATA_PROVIDED;
import static com.te.lms.constants.EmployeeAttendanceConstants.EMPLOYEE_ATTENDANCE_PROVIDED;
import static com.te.lms.constants.EmployeeConstants.EMPLOYEE_DATA_PROVIDED;
import static com.te.lms.constants.EmployeeStatusConstants.EMPLOYEE_STATUS_UPDATED;
import static com.te.lms.constants.MockConstants.MOCK_CREATED;
import static com.te.lms.constants.MockConstants.MOCK_DATA_PROVIDED;
import static com.te.lms.constants.MockRatingsConstants.MOCK_RATINGS_UPDATED;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.lms.dto.DashboardDTO;
import com.te.lms.dto.EmployeeAttendanceDTO;
import com.te.lms.dto.EmployeeDTO;
import com.te.lms.dto.MockDTO;
import com.te.lms.dto.MockDetailDTO;
import com.te.lms.dto.StatusDTO;
import com.te.lms.exception.EmployeeNotFoundException;
import com.te.lms.response.SuccessResponse;
import com.te.lms.service.BatchService;
import com.te.lms.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/mentor")
public class MentorController {
	private final BatchService batchService;
	private final EmployeeService employeeService;

	@PostMapping("/batch/gender/{batchName}")
	public ResponseEntity<SuccessResponse> getBatchGenders(@PathVariable String batchName) {
		HashMap<String, Integer> genderRatio = batchService.getBatchGender(batchName);
		return ResponseEntity.<SuccessResponse>ok().body(SuccessResponse.builder().data(genderRatio)
				.message(BATCH_DATA_PROVIDED).timestamp(LocalDateTime.now()).build());
	}

	@PostMapping("/batch/yop/{batchName}")
	public ResponseEntity<SuccessResponse> getYOPCount(@PathVariable String batchName) {
		HashMap<String, Integer> yopCount = batchService.getYOPCount(batchName);
		return ResponseEntity.<SuccessResponse>ok().body(SuccessResponse.builder().data(yopCount)
				.message(BATCH_DATA_PROVIDED).timestamp(LocalDateTime.now()).build());
	}

	@PostMapping("/batch/experienece/{batchName}")
	public ResponseEntity<SuccessResponse> getExperiences(@PathVariable String batchName) {
		HashMap<String, Integer> experienceCount = batchService.getExperienceCount(batchName);
		return ResponseEntity.<SuccessResponse>ok().body(SuccessResponse.builder().data(experienceCount)
				.message(BATCH_DATA_PROVIDED).timestamp(LocalDateTime.now()).build());
	}

	@PostMapping("/batch/degree/{batchName}")
	public ResponseEntity<SuccessResponse> getDegree(@PathVariable String batchName) {
		HashMap<String, Integer> degrees = batchService.getDegree(batchName);
		return ResponseEntity.<SuccessResponse>ok().body(SuccessResponse.builder().data(degrees)
				.message(BATCH_DATA_PROVIDED).timestamp(LocalDateTime.now()).build());
	}

	@PostMapping("/batch/performance/{batchName}")
	public ResponseEntity<SuccessResponse> getBatchPerformances(@PathVariable String batchName) {
		HashMap<String, Integer> batchPerformances = batchService.getBatchPerformance(batchName);
		return ResponseEntity.<SuccessResponse>ok().body(SuccessResponse.builder().data(batchPerformances)
				.message(BATCH_DATA_PROVIDED).timestamp(LocalDateTime.now()).build());
	}

	@PostMapping("batch/emlpoyee-list/{batchId}")
	public ResponseEntity<SuccessResponse> getEmployeeList(@PathVariable String batchId) {
		List<EmployeeDTO> employees = batchService.getEmployeeList(batchId);
		return ResponseEntity.<SuccessResponse>ok().body(SuccessResponse.builder().data(employees)
				.message(EMPLOYEE_DATA_PROVIDED).timestamp(LocalDateTime.now()).build());
	}

	@PostMapping("employee/mock-ratings/{employeeId}")
	public ResponseEntity<SuccessResponse> getMockRatings(@PathVariable String employeeId) {
		List<MockDetailDTO> mockDetails = employeeService.getMockRatings(employeeId);
		return ResponseEntity.<SuccessResponse>ok().body(SuccessResponse.builder().data(mockDetails)
				.message(MOCK_DATA_PROVIDED).timestamp(LocalDateTime.now()).build());
	}

	@PostMapping("employee/attendance/{employeeId}")
	public ResponseEntity<SuccessResponse> getEmployeeAttendance(@PathVariable String employeeId) {
		EmployeeAttendanceDTO employeeAttendanceDTO = employeeService.getEmployeeAttendance(employeeId);
		return ResponseEntity.<SuccessResponse>ok().body(SuccessResponse.builder().data(employeeAttendanceDTO)
				.message(EMPLOYEE_ATTENDANCE_PROVIDED).timestamp(LocalDateTime.now()).build());
	}

	@PostMapping("batch/create/mock")
	public ResponseEntity<SuccessResponse> createMock(@RequestBody MockDTO mockDTO) {
		String batchId = batchService.createMock(mockDTO)
				.orElseThrow(() -> new EntityNotFoundException(mockDTO.getBatchId()));
		return ResponseEntity.<SuccessResponse>ok().body(
				SuccessResponse.builder().data(batchId).message(MOCK_CREATED).timestamp(LocalDateTime.now()).build());
	}

	@PutMapping("update/mock-ratings")
	public ResponseEntity<SuccessResponse> updateMockRatings(@RequestBody MockDetailDTO mockDetailDTO) {
		String employeeId = employeeService.updateMockRatings(mockDetailDTO)
				.orElseThrow(() -> new EntityNotFoundException(mockDetailDTO.getEmployeeId()));
		return ResponseEntity.<SuccessResponse>ok().body(SuccessResponse.builder().data(employeeId)
				.message(MOCK_RATINGS_UPDATED).timestamp(LocalDateTime.now()).build());
	}

	@PutMapping("update/attendance")
	public ResponseEntity<SuccessResponse> setAttendance(@RequestBody EmployeeAttendanceDTO employeeAttendanceDTO) {
		String employeeId = employeeService.setAttendance(employeeAttendanceDTO).orElseThrow(()->new EmployeeNotFoundException(employeeAttendanceDTO.getEmployeeId()));
		return ResponseEntity.<SuccessResponse>ok().body(SuccessResponse.builder().data(employeeId)
				.message(ATTENDANCE_UPDATED).timestamp(LocalDateTime.now()).build());
	}

	@PutMapping("update/status")
	public ResponseEntity<SuccessResponse> updateStatus(@RequestBody StatusDTO statusDTO) {
		String employeeId = employeeService.updateStatus(statusDTO)
				.orElseThrow(() -> new EntityNotFoundException(statusDTO.getEmployeeId()));
		return ResponseEntity.<SuccessResponse>ok().body(SuccessResponse.builder().data(employeeId)
				.message(EMPLOYEE_STATUS_UPDATED).timestamp(LocalDateTime.now()).build());

	}

	@GetMapping("dashboard/{batchName}")
	public ResponseEntity<SuccessResponse> getDashboard(@PathVariable String batchName) {
		DashboardDTO dashboard = batchService.getDashboard(batchName).orElse(new DashboardDTO());
		return ResponseEntity.<SuccessResponse>ok().body(SuccessResponse.builder().data(dashboard)
				.message("dashboard data found").timestamp(LocalDateTime.now()).build());

	}

}
