package com.te.lms.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.te.lms.dto.MockDetailDTO;
import com.te.lms.entity.MockDetail;

@Component
public class MockDetailsUtils {
	public MockDetailDTO entityToDTO(MockDetail mockDetail) {
		MockDetailDTO mockDetailDTO = new MockDetailDTO();
		BeanUtils.copyProperties(mockDetail, mockDetailDTO);
		return mockDetailDTO;
	}

	public MockDetail dtoToEntity(MockDetailDTO mockDetailDTO) {
		MockDetail mockDetail = new MockDetail();
		BeanUtils.copyProperties(mockDetailDTO, mockDetail);
		return mockDetail;
	}
}
