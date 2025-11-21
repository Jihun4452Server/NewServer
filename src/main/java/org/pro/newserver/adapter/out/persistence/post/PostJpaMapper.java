package org.pro.newserver.adapter.out.persistence.post;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.pro.newserver.domain.post.model.Post;

@Mapper(componentModel = "spring")
public interface PostJpaMapper {

  @Mapping(target = "authorId", source = "author.id")
  Post toDomain(PostJpaEntity entity);

  @Mapping(target = "author", ignore = true)
  PostJpaEntity toEntity(Post post);
}
