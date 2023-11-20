package com.te.lms.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.te.lms.dto.TechnologyDTO;
import com.te.lms.entity.Technology;

@Component
public class TechnologyUtils {

	public TechnologyDTO entityToDTO(Technology technology) {
		TechnologyDTO technologyDTO = new TechnologyDTO();
		BeanUtils.copyProperties(technology, technologyDTO);
		return technologyDTO;
	}

	public Technology dtoToEntity(TechnologyDTO technologyDTO) {
		Technology technology = new Technology();
		BeanUtils.copyProperties(technologyDTO, technology);
		return technology;
	}
}
