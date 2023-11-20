package com.te.lms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.lms.entity.Roles;

public interface RoleRepository extends JpaRepository<Roles, Integer> {

	Optional<Roles> findByRoleName(String string);

}
