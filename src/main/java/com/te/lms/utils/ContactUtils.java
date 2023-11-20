package com.te.lms.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.te.lms.dto.ContactDTO;
import com.te.lms.entity.EmployeeContact;
@Component
public class ContactUtils {
	public ContactDTO entityToDTO(EmployeeContact employeeContact) {
		ContactDTO contactDTO = new ContactDTO();
		BeanUtils.copyProperties(employeeContact, contactDTO);
		return contactDTO;
	}

	public EmployeeContact dtoToEntity(ContactDTO contactDTO) {
		EmployeeContact employeeContact = new EmployeeContact();
		BeanUtils.copyProperties(contactDTO, employeeContact);
		return employeeContact;
	}
}
