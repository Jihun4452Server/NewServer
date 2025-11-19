package org.pro.newserver.application.post;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.pro.newserver.application.post.dto.PostCommand;
import org.pro.newserver.domain.post.model.Post;
import org.pro.newserver.domain.user.model.User;

@Mapper(componentModel = "spring")
public interface PostMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "author", source = "author")
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	Post toEntity(PostCommand command, User author);
}
