package org.pro.newserver.domain.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {
	private Long id;
	private String name;
	private String email;
	private String password;
	private Gender gender;
}

