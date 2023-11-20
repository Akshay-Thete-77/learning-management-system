package com.te.lms.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.te.lms.dto.BankDTO;
import com.te.lms.entity.EmployeeBank;
@Component
public class BankUtils {
	public BankDTO entityToDTO(EmployeeBank employeeBank) {
		BankDTO bankDTO = new BankDTO();
		BeanUtils.copyProperties(employeeBank, bankDTO);
		return bankDTO;
	}

	public EmployeeBank dtoToEntity(BankDTO bankDTO) {
		EmployeeBank employeeBank = new EmployeeBank();
		BeanUtils.copyProperties(bankDTO, employeeBank);
		return employeeBank;
	}
}
