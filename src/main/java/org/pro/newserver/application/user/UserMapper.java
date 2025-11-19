package org.pro.newserver.application.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.pro.newserver.application.user.dto.UserCommand;
import org.pro.newserver.domain.user.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	@Mapping(target = "id", ignore = true)
	User toUser(UserCommand command);
}
