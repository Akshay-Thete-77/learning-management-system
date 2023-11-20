package com.te.lms.controller;

import static com.te.lms.constants.BatchConstants.BATCH_DELETED;
import static com.te.lms.constants.BatchConstants.BATCH_FOUND;
import static com.te.lms.constants.BatchConstants.BATCH_REGISTERED;
import static com.te.lms.constants.BatchConstants.BATCH_UPDATED;
import static com.te.lms.constants.MentorConstants.MENTOR_DATA_FOUND;
import static com.te.lms.constants.MentorConstants.MENTOR_DELETED;
import static com.te.lms.constants.MentorConstants.MENTOR_UPDATED;
import static com.te.lms.constants.RequestConstants.NO_REQUEST_FOUND;
import static com.te.lms.constants.RequestConstants.REQUESTS_DATA_PROVIDED;
import static com.te.lms.constants.RequestConstants.REQUEST_APPROVED;
import static com.te.lms.constants.RequestConstants.REQUEST_REJECTED;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.lms.dto.BatchDTO;
import com.te.lms.dto.MentorDTO;
import com.te.lms.dto.RejectionDTO;
import com.te.lms.dto.RequestDTO;
import com.te.lms.exception.BatchNotFoundException;
import com.te.lms.exception.EmployeeNotFoundException;
import com.te.lms.exception.MentorNotFoundException;
import com.te.lms.exception.NotDeletedException;
import com.te.lms.exception.RegistrationFailedException;
import com.te.lms.exception.UpdationFailedException;
import com.te.lms.response.SuccessResponse;
import com.te.lms.service.BatchService;
import com.te.lms.service.EmployeeService;
import com.te.lms.service.MentorService;
import com.te.lms.service.RequestService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

	private final BatchService batchService;
	private final MentorService mentorService;
	private final RequestService requestService;

	@PostMapping("/register/batch")
	public ResponseEntity<SuccessResponse> registerBatch(@RequestBody BatchDTO batchDTO) {
		String batchId = batchService.registerBatch(batchDTO)
				.orElseThrow(() -> new RegistrationFailedException(batchDTO.getBatchId()));
		return ResponseEntity.<SuccessResponse>ok(SuccessResponse.builder().data(batchId).message(BATCH_REGISTERED)
				.timestamp(LocalDateTime.now()).build());
	}

	@PutMapping("/update/batch")
	public ResponseEntity<SuccessResponse> updateBatch(@RequestBody BatchDTO batchDTO) {
		String batchId = batchService.updateBatch(batchDTO)
				.orElseThrow(() -> new UpdationFailedException(batchDTO.getBatchId()));
		return ResponseEntity.<SuccessResponse>ok().body(
				SuccessResponse.builder().data(batchId).timestamp(LocalDateTime.now()).message(BATCH_UPDATED).build());
	}

	@DeleteMapping("/delete/batch/{id}")
	public ResponseEntity<SuccessResponse> deleteBatch(@PathVariable String id) {
		String batchId = batchService.deleteBatch(id).orElseThrow(() -> new NotDeletedException(id));
		return ResponseEntity.<SuccessResponse>ok().body(
				SuccessResponse.builder().data(batchId).timestamp(LocalDateTime.now()).message(BATCH_DELETED).build());
	}

	@PostMapping("/batch/{id}")
	public ResponseEntity<SuccessResponse> searchBatch(@PathVariable String id) {
		BatchDTO batchDTO = batchService.searchBatch(id).orElseThrow(() -> new BatchNotFoundException(id));
		return ResponseEntity.<SuccessResponse>ok().body(
				SuccessResponse.builder().data(batchDTO).timestamp(LocalDateTime.now()).message(BATCH_FOUND).build());
	}

	@GetMapping("/batches")
	public ResponseEntity<SuccessResponse> getBatches() {
		List<BatchDTO> batches = batchService.getBatches();
		return ResponseEntity.<SuccessResponse>ok().body(SuccessResponse.builder().data(batchService.getBatches())
				.message(BATCH_FOUND).timestamp(LocalDateTime.now()).build());
	}

	@PostMapping("register/mentor")
	public ResponseEntity<SuccessResponse> registerMentor(@RequestBody MentorDTO mentorDTO) {
		String mentorId = mentorService.registerMentor(mentorDTO)
				.orElseThrow(() -> new RegistrationFailedException(mentorDTO.getEmployeeId()));
		return ResponseEntity.<SuccessResponse>ok().body(
				SuccessResponse.builder().data(mentorId).message(mentorId).timestamp(LocalDateTime.now()).build());

	}

	@PutMapping("/update/mentor")
	public ResponseEntity<SuccessResponse> updateMentor(@RequestBody MentorDTO mentorDTO) {
		String mentorId = mentorService.updateMentor(mentorDTO)
				.orElseThrow(() -> new UpdationFailedException(mentorDTO.getEmployeeId()));
		return ResponseEntity.<SuccessResponse>ok().body(SuccessResponse.builder().data(mentorId)
				.timestamp(LocalDateTime.now()).message(MENTOR_UPDATED).build());
	}

	@DeleteMapping("/delete/mentor/{id}")
	public ResponseEntity<SuccessResponse> deleteMentor(@PathVariable String id) {
		String mentorId = mentorService.deleteMentor(id).orElseThrow(() -> new NotDeletedException(id));
		return ResponseEntity.<SuccessResponse>ok().body(SuccessResponse.builder().data(mentorId)
				.timestamp(LocalDateTime.now()).message(MENTOR_DELETED).build());
	}

	@PostMapping("/mentor/{id}")
	public ResponseEntity<SuccessResponse> searchMentor(@PathVariable String id) {
		MentorDTO mentor = mentorService.searchMentor(id).orElseThrow(() -> new MentorNotFoundException(id));
		return ResponseEntity.<SuccessResponse>ok().body(SuccessResponse.builder().data(mentor)
				.timestamp(LocalDateTime.now()).message(MENTOR_DATA_FOUND).build());
	}

	@GetMapping("/mentors")
	public ResponseEntity<SuccessResponse> getMentors() {
		List<MentorDTO> mentors = mentorService.getMentors();
		return ResponseEntity.<SuccessResponse>ok().body(SuccessResponse.builder().data(mentors)
				.message(MENTOR_DATA_FOUND).timestamp(LocalDateTime.now()).build());
	}

	@GetMapping("employee_requests")
	public ResponseEntity<SuccessResponse> getRequests() {
		List<RequestDTO> employeeRequests = requestService.getRequests();
		if (employeeRequests != null && employeeRequests.size() == 0) {
			return ResponseEntity.<SuccessResponse>ok().body(SuccessResponse.builder().data(employeeRequests)
					.timestamp(LocalDateTime.now()).message(NO_REQUEST_FOUND).build());
		}
		return ResponseEntity.<SuccessResponse>ok().body(SuccessResponse.builder().data(employeeRequests)
				.timestamp(LocalDateTime.now()).message(REQUESTS_DATA_PROVIDED).build());
	}

	@PutMapping("/request/approve/{employeeId}")
	public ResponseEntity<SuccessResponse> approveRequest(@PathVariable String employeeId,
			@RequestBody BatchDTO batchDTO) {
		String id = requestService.approveRequest(employeeId, batchDTO)
				.orElseThrow(() -> new EmployeeNotFoundException(employeeId));
		return ResponseEntity.<SuccessResponse>ok()
				.body(SuccessResponse.builder().data("employee with id " + id + " approved ")
						.timestamp(LocalDateTime.now()).message(REQUEST_APPROVED).build());
	}

	@PutMapping("/request/reject")
	public ResponseEntity<SuccessResponse> rejectRequest(@RequestBody RejectionDTO rejectionDTO) {
		String employeeId = requestService.rejectRequest(rejectionDTO)
				.orElseThrow(() -> new EmployeeNotFoundException(rejectionDTO.getRequestId()));
		return ResponseEntity.<SuccessResponse>ok().body(SuccessResponse.builder().data(employeeId)
				.timestamp(LocalDateTime.now()).message(REQUEST_REJECTED).build());
	}

}
