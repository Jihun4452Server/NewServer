package org.pro.newserver.adapter.in.web.post.dto.request;

import org.pro.newserver.application.post.dto.PostCommand;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostRequest(

    @NotBlank(message = "제목은 비어 있을 수 없습니다.")
    @Size(max = 50, message = "제목은 최대 50자까지 가능합니다.")
    String title,

    @NotBlank(message = "내용은 비어 있을 수 없습니다.")
    @Size(max = 1000, message = "내용은 최대 1000자까지 가능합니다.")
    String content

) {

  public PostCommand toCommand() {
    return PostCommand.builder()
        .title(title)
        .content(content)
        .build();
  }
}
