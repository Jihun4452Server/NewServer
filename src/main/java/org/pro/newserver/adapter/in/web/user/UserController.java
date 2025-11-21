package org.pro.newserver.adapter.in.web.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.pro.newserver.adapter.in.web.user.dto.request.LoginRequest;
import org.pro.newserver.adapter.in.web.user.dto.request.UserRequest;
import org.pro.newserver.global.dto.ResponseDto;
import org.pro.newserver.global.config.swagger.ApiErrorCodeExamples;
import org.pro.newserver.global.error.ErrorCode;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Tag(name = "유저 기능")
public class UserController {

  private final UserFacade userFacade;

  @Operation(summary = "회원 생성")
  @ApiErrorCodeExamples({
      ErrorCode.EMAIL_ALREADY_EXISTS,
      ErrorCode.USER_NAME_FAILED
  })
  @PostMapping
  public ResponseDto<Long> createUser(@Valid @RequestBody UserRequest request) {
    return userFacade.createUser(request);
  }

  @Operation(summary = "로그인")
  @ApiErrorCodeExamples({
      ErrorCode.USER_NOT_FOUND,
      ErrorCode.AUTHENTICATION_FAILED
  })
  @PostMapping("/login")
  public ResponseDto<String> login(
      @Valid @RequestBody LoginRequest request,
      HttpServletResponse response
  ) {
    return userFacade.login(request, response);
  }
}
