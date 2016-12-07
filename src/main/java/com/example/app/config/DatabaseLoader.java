package com.example.app.config;

import com.example.auth.model.User;
import com.example.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("!production")
public class DatabaseLoader implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... strings) throws Exception {
		if (userRepository.count() > 0) {
			return;
		}

		User user = new User();
		user.setEmail("a@a.com");
		user.setPassword("passwd");
		userRepository.saveAndFlush(user);
	}
}
