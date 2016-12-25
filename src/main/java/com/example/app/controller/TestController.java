package com.example.app.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@PreAuthorize("permitAll")
	@RequestMapping("/public")
	public String permitAll() {
		return "It works!";
	}
}
