package com.example.auth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity(name = "users")
@Data
@ToString(exclude = "password")
public class User {

	public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	@Column(nullable = false, unique = true)
	String email;

	@JsonIgnore
	String password;

	@Column
	Boolean enabled = true;

	public void setPassword(String password) {
		this.password = PASSWORD_ENCODER.encode(password);
	}
}
