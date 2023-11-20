package com.te.lms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "employee_bank")
public class EmployeeBank {

	@Id
	@Column(name = "account_no")
	private long BankAccountNo;

	@Column(name = "bank_name")
	private String BankName;

	@Column(name = "account_type")
	private String accountType;

	@Column(name = "ifsc_code")
	private String accountIFSCCode;

	@Column(name = "branch")
	private String bankBranch;

	@Column(name = "state")
	private String bankBranchState;

}
