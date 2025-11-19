package org.pro.newserver.application.post.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostCommand {
	private final String title;
	private final String content;
}
