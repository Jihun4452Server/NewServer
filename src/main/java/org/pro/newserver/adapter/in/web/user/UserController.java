package org.pro.newserver.adapter.in.web.user;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.pro.newserver.adapter.in.web.user.dto.request.LoginRequest;
import org.pro.newserver.adapter.in.web.user.dto.request.UserRequest;
import org.pro.newserver.global.dto.ResponseDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

  private final UserFacade userFacade;

  @Operation(summary = "회원 생성", tags = "유저 기능")
  @PostMapping
  public ResponseDto<Long> createUser(@RequestBody UserRequest request) {
    return userFacade.createUser(request);
  }

  @Operation(summary = "로그인", tags = "유저 기능")
  @PostMapping("/login")
  public ResponseDto<String> login(
      @RequestBody LoginRequest request, HttpServletResponse response) {
    return userFacade.login(request, response);
  }
}
