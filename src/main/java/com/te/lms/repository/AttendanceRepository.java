package com.te.lms.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.lms.entity.EmployeeAttendance;

public interface AttendanceRepository extends JpaRepository<EmployeeAttendance, String> {

}
