package com.te.lms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.lms.entity.AppUsers;

public interface AppUserRepository extends JpaRepository<AppUsers, String> {

	Optional<AppUsers> findByUsername(String username);

}
