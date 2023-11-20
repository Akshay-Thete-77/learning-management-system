package com.te.lms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.te.lms.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

}
