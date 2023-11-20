package com.te.lms.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@Table(name = "mentor")
public class Mentor {

	@Id
	private String employeeId;
	private String mentorName;
	private String emailId;
	@ManyToMany
	private List<Technology> employeeSkills;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "mentor")
	private List<Batch> batches;

}
