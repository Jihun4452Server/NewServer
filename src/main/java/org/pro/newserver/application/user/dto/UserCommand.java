package org.pro.newserver.application.user.dto;

import org.pro.newserver.domain.user.model.UserGender;

public class UserCommand {

	private final String name;
	private final String email;
	private final String password;
	private final UserGender gender;

	public UserCommand(String name, String email, String password, UserGender gender) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public UserGender getGender() {
		return gender;
	}
}
