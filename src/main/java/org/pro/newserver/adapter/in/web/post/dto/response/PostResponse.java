package org.pro.newserver.adapter.in.web.post.dto.response;

import lombok.Builder;
import lombok.Getter;
import org.pro.newserver.domain.post.model.Post;

@Getter
@Builder
public class PostResponse {

	private Long id;
	private String title;
	private String content;
	private Long authorId;
	private String authorName;

	public static PostResponse from(Post post) {
		return PostResponse.builder()
			.id(post.getId())
			.title(post.getTitle())
			.content(post.getContent())
			.authorId(post.getAuthor().getId())
			.authorName(post.getAuthor().getName()) // 필드명에 맞게 수정
			.build();
	}
}
