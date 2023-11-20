package com.te.lms.service.impl;

import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.te.lms.entity.AppUsers;
import com.te.lms.entity.Roles;
import com.te.lms.repository.AppUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetaisServiceImpl implements UserDetailsService {
	private final AppUserRepository appUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<AppUsers> optionalAU = appUserRepository.findByUsername(username);
		if (optionalAU.isPresent()) {
			AppUsers appUser = optionalAU.get();
			Function<Roles, SimpleGrantedAuthority> function = r -> {
				return new SimpleGrantedAuthority(r.getRoleName());
			};
			Set<SimpleGrantedAuthority> authorities = appUser.getRoles().stream().map(function)
					.collect(Collectors.toSet());

			return new User(username, appUser.getPassword(), authorities);
		}
		throw new UsernameNotFoundException("User with username '" + username + "' does not exist!");
	}

}
