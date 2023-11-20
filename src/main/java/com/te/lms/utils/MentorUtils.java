package com.te.lms.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.te.lms.dto.MentorDTO;
import com.te.lms.dto.TechnologyDTO;
import com.te.lms.entity.Mentor;
import com.te.lms.entity.Technology;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MentorUtils {

	private final TechnologyUtils technologyUtils;

	public MentorDTO entityToDTO(Mentor mentor) {
		MentorDTO mentorDTO = new MentorDTO();
		BeanUtils.copyProperties(mentor, mentorDTO);
		if (mentor.getEmployeeSkills() != null) {
			List<TechnologyDTO> technologyDTOs = mentor.getEmployeeSkills().stream()
					.map(s -> technologyUtils.entityToDTO(s)).collect(Collectors.toList());
			mentorDTO.setEmployeeSkills(technologyDTOs);
		}
		return mentorDTO;
	}

	public Mentor dtoToEntity(MentorDTO mentorDTO) {
		Mentor mentor = new Mentor();
		BeanUtils.copyProperties(mentorDTO, mentor);
		if (mentorDTO.getEmployeeSkills() != null) {
			List<Technology> technologies = mentorDTO.getEmployeeSkills().stream()
					.map(s -> technologyUtils.dtoToEntity(s)).collect(Collectors.toList());
			mentor.setEmployeeSkills(technologies);
		}
		return mentor;
	}
}
