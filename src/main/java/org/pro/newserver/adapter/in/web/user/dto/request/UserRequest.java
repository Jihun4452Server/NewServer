package org.pro.newserver.adapter.in.web.user.dto.request;

import org.pro.newserver.application.user.dto.UserCommand;
import org.pro.newserver.domain.user.model.Gender;

public record UserRequest(
	String name,
	String email,
	String password,
	Gender gender
) {
	public UserCommand toCommand() {
		return new UserCommand(name, email, password, gender);
	}
}
