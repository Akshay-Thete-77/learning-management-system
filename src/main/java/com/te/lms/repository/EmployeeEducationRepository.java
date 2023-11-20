package com.te.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.lms.entity.Employee;
import com.te.lms.entity.EmployeeEducation;

public interface EmployeeEducationRepository extends JpaRepository<EmployeeEducation, String> {

	List<EmployeeEducation> findByEmployee(Employee employee);

}
