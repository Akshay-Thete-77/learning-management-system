package com.te.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.lms.entity.EmployeeRequest;

public interface RequestRepository extends JpaRepository<EmployeeRequest, String> {

}
