package com.te.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.te.lms.dto.BatchDTO;
import com.te.lms.dto.DashboardDTO;
import com.te.lms.dto.EmployeeDTO;
import com.te.lms.dto.MentorDTO;
import com.te.lms.dto.MockDTO;
import com.te.lms.entity.Batch;

public interface BatchService {

	Optional<String> registerBatch(BatchDTO batchDTO);

	Optional<String> updateBatch(BatchDTO batchDTO);

	Optional<String> deleteBatch(String id);

	Optional<BatchDTO> searchBatch(String id);

	List<BatchDTO> getBatches();

	HashMap<String, Integer> getBatchGender(String batch);

	HashMap<String, Integer> getYOPCount(String batchName);

	HashMap<String, Integer> getExperienceCount(String batchName);

	HashMap<String, Integer> getDegree(String batchName);

	HashMap<String, Integer> getBatchPerformance(String batchName);

	List<EmployeeDTO> getEmployeeList(String batchId);

	Optional<String> createMock(MockDTO mockDTO);

	Optional<DashboardDTO> getDashboard(String batchName);

}
