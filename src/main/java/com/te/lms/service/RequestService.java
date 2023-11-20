package com.te.lms.service;

import java.util.List;
import java.util.Optional;

import com.te.lms.dto.BatchDTO;
import com.te.lms.dto.RejectionDTO;
import com.te.lms.dto.RequestDTO;

public interface RequestService {

	List<RequestDTO> getRequests();

	Optional<String> approveRequest(String employeeId, BatchDTO batchDTO);

	Optional<String> rejectRequest(RejectionDTO rejectionDTO);

}
