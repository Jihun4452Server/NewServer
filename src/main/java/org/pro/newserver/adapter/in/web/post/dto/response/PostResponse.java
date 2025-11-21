package org.pro.newserver.adapter.in.web.post.dto.response;

import java.time.LocalDateTime;
import org.pro.newserver.domain.post.model.Post;

public record PostResponse(
    Long id,
    Long authorId,
    String title,
    String content,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {

  public static PostResponse from(Post post) {
    return new PostResponse(
        post.getId(),
        post.getAuthorId(),
        post.getTitle(),
        post.getContent(),
        post.getCreatedAt(),
        post.getUpdatedAt()
    );
  }
}
