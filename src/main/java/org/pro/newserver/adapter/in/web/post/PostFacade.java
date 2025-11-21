package org.pro.newserver.adapter.in.web.post;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.pro.newserver.adapter.in.web.post.dto.request.PostRequest;
import org.pro.newserver.adapter.in.web.post.dto.response.PostResponse;
import org.pro.newserver.application.post.dto.PostCommand;
import org.pro.newserver.application.post.service.PostService;
import org.pro.newserver.domain.post.model.Post;
import org.pro.newserver.global.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostFacade {

	private final PostService postService;

	@Transactional
	public ResponseDto<PostResponse> createPost(Long currentUserId, @Valid PostRequest request) {
		PostCommand command = request.toCommand();

		Post post = postService.createPost(currentUserId, command);
		PostResponse response = PostResponse.from(post);

		return ResponseDto.of(HttpStatus.OK, "게시글 생성 성공", response);
	}

	@Transactional(readOnly = true)
	public ResponseDto<PostResponse> getPost(Long postId) {
		Post post = postService.getPost(postId);
		PostResponse response = PostResponse.from(post);

		return ResponseDto.of(HttpStatus.OK, "게시글 조회 성공", response);
	}

	@Transactional
	public ResponseDto<PostResponse> updatePost(
		Long postId,
		Long currentUserId,
		@Valid PostRequest request
	) {
		PostCommand command = request.toCommand();

		Post post = postService.updatePost(postId, currentUserId, command);
		PostResponse response = PostResponse.from(post);

		return ResponseDto.of(HttpStatus.OK, "게시글 수정 성공", response);
	}

	@Transactional
	public ResponseDto<String> deletePost(Long postId, Long currentUserId) {
		postService.deletePost(postId, currentUserId);
		return ResponseDto.of(HttpStatus.OK, "게시글 삭제 성공");
	}
}
