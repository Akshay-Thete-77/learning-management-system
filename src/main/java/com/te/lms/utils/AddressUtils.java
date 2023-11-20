package com.te.lms.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.te.lms.dto.AddressDTO;
import com.te.lms.entity.EmployeeAddress;
@Component
public class AddressUtils {
	public AddressDTO entityToDTO(EmployeeAddress employeeAddress) {
		AddressDTO addressDTO = new AddressDTO();
		BeanUtils.copyProperties(employeeAddress, addressDTO);
		return addressDTO;
	}

	public EmployeeAddress dtoToEntity(AddressDTO addressDTO) {
		EmployeeAddress employeeAddress = new EmployeeAddress();
		BeanUtils.copyProperties(addressDTO, employeeAddress);
		return employeeAddress;
	}
}
