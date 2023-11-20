package com.te.lms.service;

import java.util.List;
import java.util.Optional;

import com.te.lms.dto.MentorDTO;
import com.te.lms.entity.Mentor;

public interface MentorService {

	Optional<String> registerMentor(MentorDTO mentorDTO);

	Optional<String> updateMentor(MentorDTO mentorDTO);

	Optional<String> deleteMentor(String id);

	Optional<MentorDTO> searchMentor(String id);

	List<MentorDTO> getMentors();

}
