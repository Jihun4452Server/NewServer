package org.pro.newserver.application.post.service.impl;

import lombok.RequiredArgsConstructor;
import org.pro.newserver.application.post.PostMapper;
import org.pro.newserver.application.post.dto.PostCommand;
import org.pro.newserver.application.post.service.PostService;
import org.pro.newserver.application.post.validator.PostValidator;
import org.pro.newserver.application.user.validator.UserValidator;
import org.pro.newserver.domain.post.infrastructure.PostRepository;
import org.pro.newserver.domain.post.model.Post;
import org.pro.newserver.domain.user.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService {

  private final PostRepository postRepository;
  private final PostMapper postMapper;
  private final PostValidator postValidator;
  private final UserValidator userValidator;

  @Override
  public Post createPost(Long currentUserId, PostCommand command) {
    postValidator.validateCommand(command);
    User author = userValidator.getUserByIdOrThrow(currentUserId);
    Post post = postMapper.toDomain(command, author);
    return postRepository.save(post);
  }

  @Override
  @Transactional(readOnly = true)
  public Post getPost(Long postId) {
    return postValidator.validatePostExist(postId);
  }

  @Override
  public Post updatePost(Long postId, Long currentUserId, PostCommand command) {
    postValidator.validateCommand(command);
    Post post = postValidator.validatePostExist(postId);
    postValidator.validatePostOwner(post, currentUserId);
    post.update(command.getTitle(), command.getContent());
    return postRepository.save(post);
  }

  @Override
  public void deletePost(Long postId, Long currentUserId) {
    Post post = postValidator.validatePostExist(postId);
    postValidator.validatePostOwner(post, currentUserId);
    postRepository.deleteById(post.getId());
  }
}
