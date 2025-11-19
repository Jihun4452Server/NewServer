package org.pro.newserver.adapter.in.web.post.dto.request;

import org.pro.newserver.application.post.dto.PostCommand;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostRequest {

	@NotBlank
	private String title;

	@NotBlank
	private String content;

	public PostCommand toCommand() {
		return PostCommand.builder()
			.title(title)
			.content(content)
			.build();
	}
}
