package org.pro.newserver.global.config.swagger;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.pro.newserver.global.error.handler.ErrorResponse;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ApiResponses({
	@ApiResponse(
		responseCode = "400",
		description = "잘못된 요청 값(검증 에러 등)",
		content =
		@Content(
			mediaType = "application/json",
			schema = @Schema(implementation = ErrorResponse.class)))
	,
	@ApiResponse(
		responseCode = "401",
		description = "인증 실패(토큰 없음/만료 등)",
		content =
		@Content(
			mediaType = "application/json",
			schema = @Schema(implementation = ErrorResponse.class)))
	,
	@ApiResponse(
		responseCode = "403",
		description = "권한 없음(다른 사용자의 리소스 접근 등)",
		content =
		@Content(
			mediaType = "application/json",
			schema = @Schema(implementation = ErrorResponse.class)))
	,
	@ApiResponse(
		responseCode = "500",
		description = "서버 내부 오류",
		content =
		@Content(
			mediaType = "application/json",
			schema = @Schema(implementation = ErrorResponse.class)))
})
public @interface CommonErrorResponses {
}
