package com.example.auth.controller;

import com.example.auth.dto.UserCredentialsDTO;
import com.example.auth.dto.UserRegisterDTO;
import com.example.auth.model.User;
import com.example.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	@Qualifier("localAuthServer")
	private ResourceOwnerPasswordResourceDetails resourceDetails;

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<OAuth2AccessToken> login(@Valid @RequestBody UserCredentialsDTO credentials) throws Throwable {
		resourceDetails.setUsername(credentials.getUsername());
		resourceDetails.setPassword(credentials.getPassword());

		OAuth2RestTemplate template = new OAuth2RestTemplate(resourceDetails, new DefaultOAuth2ClientContext());
		template.setAccessTokenProvider(new ResourceOwnerPasswordAccessTokenProvider());

		try {
			return new ResponseEntity<OAuth2AccessToken>(template.getAccessToken(), HttpStatus.OK);
		} catch (Exception ex) {
			throw ex.getCause(); // because we want to know origin exception
		}
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<User> register(@Valid @RequestBody UserRegisterDTO data) throws Throwable {
		User user = new User();
		user.setEmail(data.getEmail());
		user.setPassword(data.getPassword());
		userRepository.saveAndFlush(user);

		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
}
