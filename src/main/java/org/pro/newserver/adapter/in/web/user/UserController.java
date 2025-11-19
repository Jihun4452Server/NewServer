package org.pro.newserver.adapter.in.web.user;

import lombok.RequiredArgsConstructor;
import org.pro.newserver.adapter.in.web.user.dto.request.UserRequest;
import org.pro.newserver.application.user.service.UserService;
import org.pro.newserver.global.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;

	@Operation(summary = "회원 생성", tags = "유저 기능")
	@PostMapping
	public ResponseDto<Long> createUser(@RequestBody UserRequest request) {
		Long userId = userService.saveUser(request.toCommand());
		return ResponseDto.of(HttpStatus.OK, String.valueOf(userId));
	}

	@Operation(summary = "이름 + 비밀번호로 이메일 찾기", tags = "유저 기능")
	@GetMapping("/find-email")
	public ResponseDto<String> findEmail(
		@RequestParam String name
	) {
		return ResponseDto.of(
			HttpStatus.OK,
			userService.findEmailByName(name)
		);
	}
}