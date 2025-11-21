package org.pro.newserver.application.post.validator;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.pro.newserver.application.post.dto.PostCommand;
import org.pro.newserver.domain.post.infrastructure.PostRepository;
import org.pro.newserver.domain.post.model.Post;
import org.pro.newserver.global.error.ErrorCode;
import org.pro.newserver.global.error.exception.BusinessException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostValidator {

  private final Validator validator;
  private final PostRepository postRepository;

  public void validateCommand(PostCommand command) {
    Set<ConstraintViolation<PostCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }

  public Post validatePostExist(Long postId) {
    return postRepository
        .findById(postId)
        .orElseThrow(() -> new BusinessException(ErrorCode.POST_NOT_FOUND));
  }

  public void validatePostOwner(Post post, Long currentUserId) {
    if (!post.getAuthorId().equals(currentUserId)) {
      throw new BusinessException(ErrorCode.HANDLE_ACCESS_DENIED);
    }
  }
}
