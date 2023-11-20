package com.te.lms.dto;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;

import com.te.lms.entity.Batch;

public class BatchStrengthDTO {
	private int batchStrengthId;
	private int initialStrength;
	private int dropOut;
	private int terminated;
	private int absconding;
	private int presentStrength;
//	private BatchDTO batch;
}
