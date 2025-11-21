package org.pro.newserver.adapter.in.web.post.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.pro.newserver.application.post.dto.PostCommand;

@Getter
@NoArgsConstructor
public class PostRequest {

  @NotBlank private String title;

  @NotBlank private String content;

  public PostCommand toCommand() {
    return PostCommand.builder().title(title).content(content).build();
  }
}
