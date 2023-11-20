package com.te.lms.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.te.lms.dto.MockDTO;
import com.te.lms.entity.Mock;
@Component
public class MockUtils {
	public MockDTO entityTODTO(Mock mock) {
		MockDTO mockDTO = new MockDTO();
		BeanUtils.copyProperties(mock, mockDTO);
		return mockDTO;
	}

	public Mock dtoTOEntity(MockDTO mockDTO) {
		Mock mock = new Mock();
		BeanUtils.copyProperties(mockDTO, mock);
		return mock;
	}
}
