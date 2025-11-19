package org.pro.newserver.domain.user.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class User {
	private final Long id;
	private final String name;
	private final String email;
	private final String password;
	private final UserGender gender;

	@Builder
	private User(Long id, String name, String email, String password, UserGender gender) {
		validateName(name);
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.gender = gender;
	}
}
