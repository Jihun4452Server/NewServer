package org.pro.newserver.adapter.in.web.post.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.pro.newserver.application.post.dto.PostCommand;

public record PostRequest(
    @NotBlank String title,
    @NotBlank String content
) {

  public PostCommand toCommand() {
    return PostCommand.builder()
        .title(title)
        .content(content)
        .build();
  }
}
