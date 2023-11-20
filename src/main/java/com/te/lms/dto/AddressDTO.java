package com.te.lms.dto;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AddressDTO {

	private String employeeId;
	private String addressType;
	private int doorNo;
	private String addressStreet;
	private String addressLocality;
	@Column(name = "city")
	private String addressCity;
	@Column(name = "state")
	private String addressState;
	@Column(name = "pin_code")
	private int pinCode;
	@Column(name = "land_mark")
	private String landMark;
}
