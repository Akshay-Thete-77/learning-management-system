package com.te.lms.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.te.lms.entity.Batch;
import com.te.lms.entity.Mock;
import com.te.lms.entity.MockDetail;
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
public class MockDetailDTO {
	private MockType mockType;
	private int mockTaken;
	private String mockTakenBy;
	private String technology;
	private int practicalKnowledge;
	private int theoroticalKnowledge;
	private OverallFeedback overallFeedback;
	private String detailedFeedback;
	private String employeeId;
	
}
