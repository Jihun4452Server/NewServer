package org.pro.newserver.application.user.service;

import org.pro.newserver.application.user.dto.UserCommand;

public interface UserService {

	Long saveUser(UserCommand command);

	String findEmailByNameAndPassword(String name, String password);

	boolean isEmailDuplicated(String email);
}
