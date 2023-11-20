package com.te.lms.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class Mock {
	@Id
	@GeneratedValue
	private int mockId;
	private String batchId;
	private int mockNo;
	private String technology;
	private String panel;
	private LocalDateTime dateAndTime;
	@OneToMany(mappedBy = "mock", cascade = CascadeType.ALL)
	private List<MockDetail> mockDetails;
	@ManyToOne(cascade = CascadeType.ALL)
	private Batch batch;
}
