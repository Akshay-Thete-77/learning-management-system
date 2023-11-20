package com.te.lms.service.impl;

import static com.te.lms.constants.BatchConstants.BATCH_DELETED;
import static com.te.lms.constants.BatchConstants.BATCH_ID_NOT_FOUND;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.te.lms.dto.BatchDTO;
import com.te.lms.dto.DashboardDTO;
import com.te.lms.dto.EmployeeDTO;
import com.te.lms.dto.MockDTO;
import com.te.lms.entity.Batch;
import com.te.lms.entity.Mentor;
import com.te.lms.entity.Mock;
import com.te.lms.entity.Technology;
import com.te.lms.enums.EducationType;
import com.te.lms.enums.Specialization;
import com.te.lms.exception.BatchNotFoundException;
import com.te.lms.exception.MentorNotFoundException;
import com.te.lms.repository.BatchRepository;
import com.te.lms.repository.EmployeeRepository;
import com.te.lms.repository.MentorRepository;
import com.te.lms.repository.TechnologyRepository;
import com.te.lms.service.BatchService;
import com.te.lms.utils.BatchUtils;
import com.te.lms.utils.EmployeeUtils;
import com.te.lms.utils.MockUtils;
import com.te.lms.utils.TechnologyUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BatchServiceImpl implements BatchService {

	private final BatchRepository batchRepository;
	private final EmployeeRepository employeeRepository;
	private final MentorRepository mentorRepository;
	private final TechnologyRepository technologyRepository;
	private final BatchUtils batchUtils;
	private final EmployeeUtils employeeUtils;
	private final MockUtils mockUtils;
	private final TechnologyUtils technologyUtils;

	@Override
	public Optional<String> registerBatch(BatchDTO batchDTO) {
		Batch batch = new Batch();
		BeanUtils.copyProperties(batchDTO, batch);
		Mentor mentor = mentorRepository.findByMentorName(batchDTO.getMentorName())
				.orElseThrow(() -> new MentorNotFoundException(batchDTO.getMentorName()));
		batch.setMentor(mentor);

		List<Technology> technologies = batchDTO.getTechnologies().stream()
				.map(t -> technologyRepository.findById(t.getTechnologyName()).orElse(
						Technology.builder().technologyName(t.getTechnologyName()).batches(new ArrayList<>()).build()))
				.collect(Collectors.toList());
		batch.setTechnologies(technologies);
		technologies.stream().forEach(t -> t.getBatches().add(batch));
		return Optional.ofNullable(batchRepository.save(batch).getBatchId());
	}

	@Override
	public Optional<String> updateBatch(BatchDTO batchDTO) {
		Batch batch = new Batch();
		BeanUtils.copyProperties(batchDTO, batch);
		return Optional.ofNullable(batchRepository.save(batch).getBatchId());
	}

	@Override
	public Optional<String> deleteBatch( String id) {
		Batch batch = batchRepository.findById(id).orElseThrow(() -> new BatchNotFoundException(id));
		batchRepository.delete(batch);
		return Optional.ofNullable(BATCH_DELETED + " with id " + id);
	}

	@Override
	public Optional<BatchDTO> searchBatch(String id) {
		Batch batch = batchRepository.findById(id).orElseThrow(() -> new BatchNotFoundException(id));
		BatchDTO batchDTO = batchUtils.entityTODTO(batch);
		return Optional.ofNullable(batchDTO);
	}

	@Override
	public List<BatchDTO> getBatches() {
		List<Batch> batches = batchRepository.findAll();
		List<BatchDTO> batchDTOs = batches.stream().map(b -> batchUtils.entityTODTO(b)).collect(Collectors.toList());
		return batchDTOs;
	}

	@Override
	public HashMap<String, Integer> getBatchGender(String batchName) {
		Batch batch = batchRepository.findByBatchName(batchName)
				.orElseThrow(() -> new BatchNotFoundException(batchName));
		int male = (int) batch.getEmployees().stream().filter(e -> e.getEmployeeGender().equalsIgnoreCase("male"))
				.count();
		int female = batch.getEmployees().size() - male;
		HashMap<String, Integer> batchGender = new HashMap<String, Integer>();
		batchGender.put("male", male);
		batchGender.put("female", female);
		return batchGender;
	}

	@Override
	public HashMap<String, Integer> getYOPCount(String batchName) {
		Batch batch = batchRepository.findByBatchName(batchName)
				.orElseThrow(() -> new BatchNotFoundException(batchName));
		HashMap<String, Integer> yopCount = new HashMap<>();

		batch.getEmployees().stream().forEach(e -> {
			String yearOfPassing = String
					.valueOf(e.getEmployeeEducations().get(e.getEmployeeEducations().size() - 1).getYearOfPassing());
			if (yopCount.containsKey(yearOfPassing))
				yopCount.put(yearOfPassing, yopCount.get(yearOfPassing) + 1);
			else
				yopCount.put(yearOfPassing, 1);
		});

		return yopCount;
	}

	@Override
	public HashMap<String, Integer> getExperienceCount(String batchName) {
		Batch batch = batchRepository.findByBatchName(batchName)
				.orElseThrow(() -> new BatchNotFoundException(batchName));
		HashMap<String, Integer> experienceCount = new HashMap<>();

		batch.getEmployees().stream().forEach(e -> {
			String experience = String
					.valueOf(e.getEmployeeExperiences().get(e.getEmployeeExperiences().size() - 1).getExperienceId());
			if (experienceCount.containsKey(experience))
				experienceCount.put(experience, experienceCount.get(experience) + 1);
			else
				experienceCount.put(experience, 1);
		});
		return experienceCount;
	}

	@Override
	public HashMap<String, Integer> getDegree(String batchName) {
		Batch batch = batchRepository.findByBatchName(batchName)
				.orElseThrow(() -> new BatchNotFoundException(batchName));
		HashMap<String, Integer> degrees = new HashMap<>();

		batch.getEmployees().stream().forEach(e -> {
			e.getEmployeeEducations().stream().filter(edu -> edu.getSpecialization().equals(Specialization.CSE))
					.forEach(edu -> {
						if (degrees.containsKey("BE(CSE)"))
							degrees.put("BE(CSE)", degrees.get("BE(CSE)") + 1);
						else {
							degrees.put("BE(CSE)", 1);
						}
					});
		});

		batch.getEmployees().stream().forEach(e -> {
			e.getEmployeeEducations().stream().filter(edu -> edu.getEducationType().equals(EducationType.GRADUATION)
					&& !edu.getSpecialization().equals(Specialization.CSE)).forEach(edu -> {
						if (degrees.containsKey("BE(Non-CSE)"))
							degrees.put("BE(Non-CSE)", degrees.get("BE(Non-CSE)") + 1);
						else {
							degrees.put("BE(Non-CSE)", 1);
						}
					});
		});

		batch.getEmployees().stream().forEach(e -> {
			e.getEmployeeEducations().stream()
					.filter(edu -> edu.getEducationType().equals(EducationType.POST_GRADUCATION)).forEach(edu -> {
						if (degrees.containsKey("Post Graduates"))
							degrees.put("Post Graduates", degrees.get("Post Graduates") + 1);
						else {
							degrees.put("Post Graduates", 1);
						}
					});
		});

		batch.getEmployees().stream().forEach(e -> {
			e.getEmployeeEducations().stream().filter(edu -> edu.getEducationType().equals(EducationType.PHD))
					.forEach(edu -> {
						if (degrees.containsKey("PHD"))
							degrees.put("PHD", degrees.get("PHD") + 1);
						else {
							degrees.put("PHD", 1);
						}
					});
		});

		return degrees;
	}

	@Override
	public HashMap<String, Integer> getBatchPerformance(String batchName) {
		Batch batch = batchRepository.findByBatchName(batchName)
				.orElseThrow(() -> new BatchNotFoundException(batchName));
		HashMap<String, Integer> batchPerformances = new HashMap<>();

		batch.getEmployees().stream()
				.map(e -> e.getMockDetails().get(e.getMockDetails().size() - 1).getOverallFeedback().toString())
				.forEach(o -> {
					if (batchPerformances.containsKey(o))
						batchPerformances.put(o, batchPerformances.get(o) + 1);
					else
						batchPerformances.put(o, 1);
				});

		return batchPerformances;
	}

	@Override
	public List<EmployeeDTO> getEmployeeList(String batchId) {
		Batch batch = batchRepository.findById(batchId).orElseThrow(() -> new BatchNotFoundException(batchId));
		List<EmployeeDTO> emplooyeeDTOs = batch.getEmployees().stream().map((e) -> employeeUtils.entityToDTO(e))
				.collect(Collectors.toList());
		return emplooyeeDTOs;
	}

	@Override
	public Optional<String> createMock(MockDTO mockDTO) {
		Batch batch = batchRepository.findById(mockDTO.getBatchId())
				.orElseThrow(() -> new BatchNotFoundException(mockDTO.getBatchId()));
		Mock mock = mockUtils.dtoTOEntity(mockDTO);
		mock.setBatch(batch);
		batch.getMocks().add(mock);
		return Optional.ofNullable(batchRepository.save(batch).getBatchId());
	}

	@Override
	public Optional<DashboardDTO> getDashboard(String batchName) {
		DashboardDTO dashboardDTO=new DashboardDTO();
		dashboardDTO.setBatchGender(getBatchGender(batchName));
		dashboardDTO.setBatchPerformances(getBatchPerformance(batchName));
		dashboardDTO.setDegrees(getDegree(batchName));
		dashboardDTO.setExperienceCount(getExperienceCount(batchName));
		dashboardDTO.setYopCount(getYOPCount(batchName));
		return Optional.ofNullable(dashboardDTO);
	}

}
