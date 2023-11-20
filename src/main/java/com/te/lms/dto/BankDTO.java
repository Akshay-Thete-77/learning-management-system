package com.te.lms.dto;

import javax.persistence.Column;
import javax.persistence.Id;

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
public class BankDTO {

	private String employeeId;
	private long BankAccountNo;
	private String BankName;
	private String accountType;
	private String accountIFSCCode;
	private String bankBranch;
	private String bankBranchState;
}
