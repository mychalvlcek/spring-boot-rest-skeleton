package com.example.auth.provider;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Data
public class AuthUser extends User {

	private final Long id;

	public AuthUser(Long id, String username, String password, boolean enabled, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, true, true, true,  authorities);
		this.id = id;
	}
}
