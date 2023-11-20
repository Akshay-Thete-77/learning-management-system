package com.te.lms.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
@Entity
@Table(name = "employee_address")
public class EmployeeAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq")
	@SequenceGenerator(name = "address_seq", allocationSize = 1, initialValue = 1)
	@Column(name = "address_id")
	private Integer addressId;
	@Column(name = "address_type")
	private String addressType;

	@Column(name = "door_no")
	private int doorNo;

	@Column(name = "address_street")
	private String addressStreet;

	@Column(name = "address_locality")
	private String addressLocality;

	@Column(name = "city")
	private String addressCity;

	@Column(name = "state")
	private String addressState;

	@Column(name = "pin_code")
	private int pinCode;

	@Column(name = "land_mark")
	private String landMark;
	
	@ManyToMany(cascade = CascadeType.ALL,mappedBy = "employeeAddresses")
	private List<Employee> employees;

}