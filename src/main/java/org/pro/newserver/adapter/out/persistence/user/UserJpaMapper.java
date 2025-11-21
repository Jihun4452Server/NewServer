package org.pro.newserver.adapter.out.persistence.user;

import org.mapstruct.Mapper;
import org.pro.newserver.domain.user.model.User;

@Mapper(componentModel = "spring")
public interface UserJpaMapper {

  UserJpaEntity toEntity(User user);

  User toDomain(UserJpaEntity entity);
}
