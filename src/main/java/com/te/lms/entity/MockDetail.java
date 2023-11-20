package com.te.lms.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.te.lms.enums.MockType;
import com.te.lms.enums.OverallFeedback;

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
public class MockDetail {
	@Id
	@GeneratedValue
	private int mockDetailsId;
	private int mockTaken;
	private MockType mockType;
	private String mockTakenBy;
	private String technology;
	private int practicalKnowledge;
	private int theoroticalKnowledge;
	private OverallFeedback overallFeedback;
	private String detailedFeedback;
	@ManyToOne(cascade = CascadeType.ALL)
	private Mock mock;
	@ManyToOne(cascade = CascadeType.ALL)
	private Employee employee;

}
