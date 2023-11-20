package com.te.lms.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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
public class BatchStrength {
	@Id
	@GeneratedValue
	private int batchStrengthId;
	private int initialStrength;
	private int dropOut;
	private int terminatedEmp;
	private int absconding;
	private int presentStrength;
	@OneToOne(mappedBy = "batchStrength")
	private Batch batch;

}
