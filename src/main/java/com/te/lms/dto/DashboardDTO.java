package com.te.lms.dto;

import java.util.HashMap;

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
public class DashboardDTO {
	HashMap<String, Integer> yopCount;
	HashMap<String, Integer> experienceCount;
	HashMap<String, Integer> degrees;
	HashMap<String, Integer> batchPerformances;
	HashMap<String, Integer> batchGender;
}
