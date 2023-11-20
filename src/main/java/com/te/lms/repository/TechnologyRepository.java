package com.te.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.lms.entity.Technology;

public interface TechnologyRepository extends JpaRepository<Technology, String> {

}
