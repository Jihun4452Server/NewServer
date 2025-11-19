package org.pro.newserver.application.post.service;

import org.pro.newserver.application.post.dto.PostCommand;
import org.pro.newserver.domain.post.model.Post;

public interface PostService {

	Post createPost(Long currentUserId, PostCommand command);

	Post getPost(Long postId);

	Post updatePost(Long postId, Long currentUserId, PostCommand command);

	void deletePost(Long postId, Long currentUserId);
}
