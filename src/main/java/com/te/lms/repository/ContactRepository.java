package com.te.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.lms.entity.EmployeeContact;

public interface ContactRepository extends JpaRepository<EmployeeContact, String> {

}
