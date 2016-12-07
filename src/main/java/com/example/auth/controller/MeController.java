package com.example.auth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class MeController {

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping("/me")
	public Principal user(Principal principal) {
		return principal;
	}

}
