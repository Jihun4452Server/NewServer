package org.pro.newserver.adapter.in.web.post;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.pro.newserver.adapter.in.web.post.dto.request.PostRequest;
import org.pro.newserver.adapter.in.web.post.dto.response.PostResponse;
import org.pro.newserver.application.post.dto.PostCommand;
import org.pro.newserver.application.post.service.PostService;
import org.pro.newserver.global.common.CurrentUser;
import org.pro.newserver.global.dto.ResponseDto;
import org.pro.newserver.global.jwt.UserDetailsImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
@Tag(name = "게시글 기능", description = "게시글 생성 / 조회 / 수정 / 삭제 API")
public class PostController {

	private final PostService postService;

	@Operation(summary = "게시글 생성", description = "로그인한 사용자가 게시글을 생성합니다.")
	@PostMapping
	public ResponseDto<PostResponse> createPost(
		@CurrentUser UserDetailsImpl userDetails,
		@Valid @RequestBody PostRequest request
	) {
		PostCommand command = request.toCommand();
		PostResponse response = PostResponse.from(postService.createPost(userDetails.getId(), command));
		return ResponseDto.of(HttpStatus.OK, "게시글 생성 성공", response);
	}

	@Operation(summary = "게시글 조회", description = "ID로 특정 게시글을 조회합니다.")
	@GetMapping("/{postId}")
	public ResponseDto<PostResponse> getPost(@PathVariable Long postId) {
		PostResponse response = PostResponse.from(postService.getPost(postId));
		return ResponseDto.of(HttpStatus.OK, "게시글 조회 성공", response);
	}

	@Operation(summary = "게시글 수정", description = "로그인한 사용자가 본인이 작성한 게시글을 수정합니다.")
	@PutMapping("/{postId}")
	public ResponseDto<PostResponse> updatePost(
		@PathVariable Long postId,
		@CurrentUser UserDetailsImpl userDetails,
		@Valid @RequestBody PostRequest request
	) {
		PostCommand command = request.toCommand();
		PostResponse response = PostResponse.from(postService.updatePost(postId, userDetails.getId(), command));
		return ResponseDto.of(HttpStatus.OK, "게시글 수정 성공", response);
	}

	@Operation(summary = "게시글 삭제", description = "로그인한 사용자가 본인이 작성한 게시글을 삭제합니다.")
	@DeleteMapping("/{postId}")
	public ResponseDto<String> deletePost(
		@PathVariable Long postId,
		@CurrentUser UserDetailsImpl userDetails
	) {
		postService.deletePost(postId, userDetails.getId());
		return ResponseDto.of(HttpStatus.OK, "게시글 삭제 성공");
	}
}
