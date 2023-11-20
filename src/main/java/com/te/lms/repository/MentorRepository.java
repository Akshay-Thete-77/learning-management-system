package com.te.lms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.lms.entity.Mentor;

public interface MentorRepository extends JpaRepository<Mentor, String> {

	Optional<Mentor> findByMentorName(String batchIdNotFound);

}
