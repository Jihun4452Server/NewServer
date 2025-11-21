package org.pro.newserver.adapter.in.web.user.dto.request;

import org.pro.newserver.application.user.dto.UserCommand;
import org.pro.newserver.domain.user.model.Gender;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(

    @NotBlank(message = "이름은 비어 있을 수 없습니다.")
    @Size(min = 2, max = 20, message = "이름은 2~20자 사이여야 합니다.")
    String name,

    @NotBlank(message = "이메일은 비어 있을 수 없습니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    String email,

    @NotBlank(message = "비밀번호는 비어 있을 수 없습니다.")
    @Size(min = 6, max = 20, message = "비밀번호는 6~20자 사이여야 합니다.")
    String password,

    Gender gender

) {

  public UserCommand toCommand() {
    return new UserCommand(name, email, password, gender);
  }
}
