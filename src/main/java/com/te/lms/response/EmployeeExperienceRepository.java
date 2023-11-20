package com.te.lms.response;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.lms.entity.Employee;
import com.te.lms.entity.EmployeeExperience;

public interface EmployeeExperienceRepository extends JpaRepository<EmployeeExperience, Integer> {

	List<EmployeeExperience> findByEmployee(Employee employee);

}
