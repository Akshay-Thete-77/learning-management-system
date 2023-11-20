package com.te.lms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.te.lms.dto.MentorDTO;
import com.te.lms.entity.AppUsers;
import com.te.lms.entity.Mentor;
import com.te.lms.entity.Roles;
import com.te.lms.entity.Technology;
import com.te.lms.exception.MentorNotFoundException;
import com.te.lms.repository.AppUserRepository;
import com.te.lms.repository.MentorRepository;
import com.te.lms.repository.RoleRepository;
import com.te.lms.repository.TechnologyRepository;
import com.te.lms.service.MentorService;
import com.te.lms.utils.MentorUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MentorServiceImpl implements MentorService {
	private final MentorRepository mentorRepository;
	private final TechnologyRepository technologyRepository;
	private final MentorUtils mentorUtils;
	private final PasswordEncoder passwordEncoder;
	private final RoleRepository roleRepository;
	private final AppUserRepository appUserRepository;

	@Override
	public Optional<String> registerMentor(MentorDTO mentorDTO) {
		Mentor mentor = mentorUtils.dtoToEntity(mentorDTO);
		List<Technology> employeeSkills = mentor.getEmployeeSkills().stream()
				.map(s -> technologyRepository.findById(s.getTechnologyName())
						.orElse(technologyRepository.save(Technology.builder().technologyName(s.getTechnologyName()).build())))
				.collect(Collectors.toList());
		mentor.setEmployeeSkills(employeeSkills);

		Optional<Roles> mentorRole = roleRepository.findByRoleName("ROLE_MENTOR");
		if (mentorRole.isPresent()) {
			Roles roles = mentorRole.get();
			AppUsers appUser = AppUsers.builder().username(mentorDTO.getEmployeeId())
					.password(passwordEncoder.encode(mentorDTO.getPassword())).roles(new ArrayList<Roles>()).build();
			roles.getAppUsers().add(appUser);
			appUser.getRoles().add(roles);
			appUserRepository.save(appUser);
		}
			
		return Optional.ofNullable(mentorRepository.save(mentor).getEmployeeId());
	}

	@Override
	public Optional<String> updateMentor(MentorDTO mentorDTO) {
		Mentor mentor = new Mentor();
		BeanUtils.copyProperties(mentorDTO, mentor);
		return Optional.ofNullable(mentorRepository.save(mentor).getEmployeeId());
	}

	@Override
	public Optional<String> deleteMentor(String id) {
		Mentor mentor = mentorRepository.findById(id).orElseThrow(() -> new MentorNotFoundException(id));
		mentorRepository.delete(mentor);
		return Optional.ofNullable(id);
	}

	@Override
	public Optional<MentorDTO> searchMentor(String id) {
		Mentor mentor = mentorRepository.findById(id).orElseThrow(() -> new MentorNotFoundException(id));
		MentorDTO mentorDTO = mentorUtils.entityToDTO(mentor);
		return Optional.ofNullable(mentorDTO);

	}

	@Override
	public List<MentorDTO> getMentors() {
		List<Mentor> mentors = mentorRepository.findAll();
		return mentors.stream().map(m -> mentorUtils.entityToDTO(m)).collect(Collectors.toList());

	}

}
