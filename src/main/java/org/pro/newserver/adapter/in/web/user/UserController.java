package org.pro.newserver.adapter.in.web.user;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.pro.newserver.adapter.in.web.user.dto.request.LoginRequest;
import org.pro.newserver.adapter.in.web.user.dto.request.UserRequest;
import org.pro.newserver.application.user.service.UserService;
import org.pro.newserver.global.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;

import org.pro.newserver.global.jwt.JwtProvider;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;
	private final JwtProvider jwtProvider;

	@Operation(summary = "회원 생성", tags = "유저 기능")
	@PostMapping
	public ResponseDto<Long> createUser(@RequestBody UserRequest request) {
		Long userId = userService.saveUser(request.toCommand());
		return ResponseDto.of(HttpStatus.OK, String.valueOf(userId));
	}

	@Operation(summary = "로그인", tags = "유저 기능")
	@PostMapping("/login")
	public ResponseDto<String> login(
		@RequestBody LoginRequest request,
		HttpServletResponse response
	) {
		userService.login(request.email(), request.password());
		jwtProvider.issueTokensAndAddHeaders(response, request.email());
		return ResponseDto.of(HttpStatus.OK, "로그인에 성공했습니다.");
	}
}