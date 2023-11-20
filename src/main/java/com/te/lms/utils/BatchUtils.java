package com.te.lms.utils;

import java.util.List;

import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.te.lms.dto.BatchDTO;
import com.te.lms.dto.TechnologyDTO;
import com.te.lms.entity.Batch;
import com.te.lms.entity.Technology;
import com.te.lms.exception.BatchNotFoundException;
import com.te.lms.repository.MentorRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BatchUtils {
	private final MentorRepository mentorRepository;
	private final TechnologyUtils technologyUtils;

	public BatchDTO entityTODTO(Batch batch) {
		BatchDTO batchDTO = new BatchDTO();
		BeanUtils.copyProperties(batch, batchDTO);
		if (batch.getTechnologies() != null) {
			List<TechnologyDTO> technologyDTOs = batch.getTechnologies().stream()
					.map(t -> technologyUtils.entityToDTO(t)).collect(Collectors.toList());
			batchDTO.setTechnologies(technologyDTOs);
			batchDTO.setMentorName(batch.getMentor().getMentorName());
		}
		return batchDTO;
	}

	public Batch dtoToEntity(BatchDTO batchDTO) {
		Batch batch = new Batch();
		BeanUtils.copyProperties(batchDTO, batch);
		batch.setMentor(mentorRepository.findById(batchDTO.getMentorName())
				.orElseThrow(() -> new BatchNotFoundException(batchDTO.getMentorName())));
		if (batchDTO.getTechnologies() != null) {
			List<Technology> technologies = batchDTO.getTechnologies().stream().map(t -> technologyUtils.dtoToEntity(t))
					.collect(Collectors.toList());
			batch.setTechnologies(technologies);
		}
		return batch;
	}

}
