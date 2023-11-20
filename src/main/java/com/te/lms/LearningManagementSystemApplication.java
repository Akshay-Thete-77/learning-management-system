package com.te.lms;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.te.lms.entity.Admin;
import com.te.lms.entity.AppUsers;
import com.te.lms.entity.Roles;
import com.te.lms.enums.Status;
import com.te.lms.repository.AdminRepository;
import com.te.lms.repository.AppUserRepository;
import com.te.lms.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class LearningManagementSystemApplication {
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	private final AdminRepository adminRepository;
	private final AppUserRepository appUserRepository;

	public static void main(String[] args) {
		try {
			SpringApplication.run(LearningManagementSystemApplication.class, args)
					.getBean(LearningManagementSystemApplication.class).runner().run();
		} catch (BeansException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public CommandLineRunner runner() {
		return args -> {
			Roles employeeRole = Roles.builder().roleName("ROLE_EMPLOYEE").build();
			Roles adminRole = Roles.builder().roleName("ROLE_ADMIN").appUsers(new ArrayList()).build();
			Roles mentorRole = Roles.builder().roleName("ROLE_MENTOR").build();

			Admin admin;
			Optional<Admin> adminOptional = adminRepository.findById("admin01");
			if (adminOptional.isPresent())
				admin = adminOptional.get();
			else
				admin = Admin.builder().adminId("admin01").build();

			Optional<Roles> role1 = roleRepository.findByRoleName("ROLE_EMPLOYEE");
			if (role1.isPresent())
				employeeRole = role1.get();

			Optional<Roles> role2 = roleRepository.findByRoleName("ROLE_ADMIN");
			if (role2.isPresent())
				adminRole = role2.get();

			Optional<Roles> role3 = roleRepository.findByRoleName("ROLE_MENTOR");
			if (role3.isPresent())
				mentorRole = role3.get();

			roleRepository.save(employeeRole);
			roleRepository.save(mentorRole);
			adminRepository.save(admin);

			if (!appUserRepository.existsById(admin.getAdminId())) {
				AppUsers appUser = AppUsers.builder().username(admin.getAdminId())
						.password(passwordEncoder.encode("qwerty")).roles(new ArrayList<>()).build();

				appUser.getRoles().add(adminRole);
				adminRole.getAppUsers().add(appUser);

				appUserRepository.save(appUser);
			}
		};
	}

}
