package com.example.auth.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserRegisterDTO {

	@NotNull
	@NotEmpty
	@Email
	private String email;

	@NotNull
	@Size(min = 8, message = "The field must be at least {min} characters")
	private String password;

	// todo: password validation http://stackoverflow.com/questions/1972933/cross-field-validation-with-hibernate-validator-jsr-303
	private String matchingPassword;
}
