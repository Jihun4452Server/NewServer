package org.pro.newserver.adapter.in.web.post.dto.response;

import lombok.Builder;
import lombok.Getter;
import org.pro.newserver.domain.post.model.Post;

import java.time.LocalDateTime;

@Getter
@Builder
public class PostResponse {

	private final Long id;
	private final Long authorId;
	private final String title;
	private final String content;
	private final LocalDateTime createdAt;
	private final LocalDateTime updatedAt;

	public static PostResponse from(Post post) {
		return PostResponse.builder()
			.id(post.getId())
			.authorId(post.getAuthorId())
			.title(post.getTitle())
			.content(post.getContent())
			.createdAt(post.getCreatedAt())
			.updatedAt(post.getUpdatedAt())
			.build();
	}
}
