package com.te.lms.dto;

import java.time.LocalDateTime;

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
public class MockDTO {
	private String batchId;
	private int mockNo;
	private String technology;
	private String panel;
	private LocalDateTime dateAndTime;

}
