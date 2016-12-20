package com.example.auth.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserCredentialsDTO implements Serializable {

	@NotNull
	private String username;

	@NotNull
	private String password;
}
