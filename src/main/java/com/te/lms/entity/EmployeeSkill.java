package com.te.lms.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
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
@Table(name = "employee_skills")
public class EmployeeSkill {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "skill_seq")
	@SequenceGenerator(name = "skill_seq", allocationSize = 1, initialValue = 1)
	@Column(name = "skill_id")
	private Integer skillId;

	@Column(name = "skill_type")
	private String skillType;

	@Column(name = "skill_rating")
	private String skillRating;

	@Column(name = "year_of_experience")
	private double yearOfExperience;

}
