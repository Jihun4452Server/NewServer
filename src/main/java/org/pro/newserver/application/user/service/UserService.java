package org.pro.newserver.application.user.service;

import org.pro.newserver.application.user.dto.UserCommand;

public interface UserService {

	Long saveUser(UserCommand command);

	String findEmailByName(String name);

	void login(String email, String password);
}
