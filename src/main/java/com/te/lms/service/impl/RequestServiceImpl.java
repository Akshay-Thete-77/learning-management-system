package com.te.lms.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.te.lms.dto.BatchDTO;
import com.te.lms.dto.RejectionDTO;
import com.te.lms.dto.RequestDTO;
import com.te.lms.entity.Batch;
import com.te.lms.entity.Employee;
import com.te.lms.entity.EmployeeRequest;
import com.te.lms.exception.BatchNotFoundException;
import com.te.lms.exception.RequestNotFoundException;
import com.te.lms.repository.BatchRepository;
import com.te.lms.repository.EmployeeRepository;
import com.te.lms.repository.RequestRepository;
import com.te.lms.service.RequestService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

	private final RequestRepository requestRepository;
	private final BatchRepository batchRepository;
	private final EmployeeRepository employeeRepository;

	@Override
	public List<RequestDTO> getRequests() {
		List<EmployeeRequest> employeeRequests = requestRepository.findAll();
		List<RequestDTO> reuqestDTOs = employeeRequests.stream().map(r -> {
			RequestDTO requestDTO = new RequestDTO();
			BeanUtils.copyProperties(r, requestDTO);
			requestDTO.setEmployeeId(r.getEmployee().getEmployeeId());
			requestDTO.setEmployeeName(r.getEmployee().getEmployeeName());
			System.out.println(requestDTO.getEmployeeId() + "dto");
			System.out.println(r.getEmployee().getEmployeeId() + "entity");
			return requestDTO;
		}).collect(Collectors.toList());
		return reuqestDTOs;
	}

	@Override
	public Optional<String> approveRequest(String requestId, BatchDTO batchDTO) {
		EmployeeRequest employeeRequest = requestRepository.findById(requestId)
				.orElseThrow(() -> new RequestNotFoundException(requestId));

		Batch batch = batchRepository.findById(batchDTO.getBatchId())
				.orElseThrow(() -> new BatchNotFoundException(batchDTO.getBatchId()));

		Employee employee = employeeRequest.getEmployee();
		employee.setBatch(batch);
		batch.getEmployees().add(employee);
		employeeRepository.save(employee);
		requestRepository.delete(employeeRequest);
		return Optional.of(employee.getEmployeeId());
	}

	@Override
	public Optional<String> rejectRequest(RejectionDTO rejectionDTO) {
		EmployeeRequest employeeRequest = requestRepository.findById(rejectionDTO.getRequestId())
				.orElseThrow(() -> new RequestNotFoundException(rejectionDTO.getRequestId()));
		employeeRequest.setRejectionReason(rejectionDTO.getRejectionReason());
		return Optional.ofNullable(requestRepository.save(employeeRequest).getEmployee().getEmployeeId());
	}

}
