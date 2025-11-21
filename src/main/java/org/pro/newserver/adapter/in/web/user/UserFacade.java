package org.pro.newserver.adapter.in.web.user;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.pro.newserver.adapter.in.web.user.dto.request.LoginRequest;
import org.pro.newserver.adapter.in.web.user.dto.request.UserRequest;
import org.pro.newserver.application.user.service.UserService;
import org.pro.newserver.global.dto.ResponseDto;
import org.pro.newserver.global.jwt.JwtProvider;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {

  private final UserService userService;
  private final JwtProvider jwtProvider;

  public ResponseDto<Long> createUser(UserRequest request) {
    Long userId = userService.saveUser(request.toCommand());
    return ResponseDto.of(HttpStatus.OK, String.valueOf(userId));
  }

  public ResponseDto<String> login(LoginRequest request, HttpServletResponse response) {
    userService.login(request.email(), request.password());
    jwtProvider.issueTokensAndAddHeaders(response, request.email());
    return ResponseDto.of(HttpStatus.OK, "로그인에 성공했습니다.");
  }
}
