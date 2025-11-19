package org.pro.newserver.application.user;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.pro.newserver.application.user.dto.UserCommand;
import org.pro.newserver.domain.user.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public interface UserMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "password",
		expression = "java(passwordEncoder.encode(command.password()))")
	User toUser(UserCommand command, @Context PasswordEncoder passwordEncoder);
}