package com.te.lms.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
@Entity
@Table(name = "batch")
public class Batch {

	@Id
	@Column(name = "batch_id")
	private String batchId;

	@Column(name = "batch_name")
	private String batchName;

	@ManyToOne
	private Mentor mentor;

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "map_batch_tech")
	private List<Technology> technologies;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "status")
	private Status status;

	@OneToMany(mappedBy = "batch", cascade = CascadeType.ALL)
	private List<Employee> employees;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "batch")
	private List<Mock> mocks;

	@OneToOne
	private BatchStrength batchStrength;

}
