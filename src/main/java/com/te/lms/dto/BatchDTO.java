package com.te.lms.dto;

import java.util.Date;
import java.util.List;

import com.te.lms.entity.BatchStrength;
import com.te.lms.entity.Mentor;
import com.te.lms.entity.Technology;
import com.te.lms.enums.Status;

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
public class BatchDTO {

	private String batchId;
	private String batchName;
	private String mentorName;
	private List<TechnologyDTO> technologies;
	private Date startDate;
	private Date endDate;
	private Status status;
	private BatchStrengthDTO batchStrength;
}
