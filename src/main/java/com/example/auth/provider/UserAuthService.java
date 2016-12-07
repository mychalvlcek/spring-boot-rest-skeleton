package com.example.auth.provider;

import com.example.auth.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserAuthService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("Fetching user " + username);
		return userRepository
			.findByEmail(username)
			.map(u -> new AuthUser(u.getId(), u.getEmail(), u.getPassword(), u.getEnabled(), AuthorityUtils.createAuthorityList("ROLE_USER")))
			.orElseThrow(() -> new UsernameNotFoundException("Could not find the user '" + username + "'"));
	}
}
