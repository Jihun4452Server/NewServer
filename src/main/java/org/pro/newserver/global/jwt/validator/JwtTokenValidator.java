package org.pro.newserver.global.jwt.validator;

import org.pro.newserver.global.error.ErrorCode;
import org.pro.newserver.global.error.exception.JwtAuthenticationException;
import org.pro.newserver.global.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtTokenValidator {
	private final JwtProvider jwtProvider;

	@Autowired
	@Qualifier("authStringRedisTemplate")
	private RedisTemplate<String, String> redisTemplate;

	public JwtTokenValidator(JwtProvider jwtProvider) {
		this.jwtProvider = jwtProvider;
	}

	public String resolveAndValidate(HttpServletRequest request) {
		String token = null;
		if (request.getCookies() != null) {
			for (Cookie c : request.getCookies()) {
				if ("accessToken".equals(c.getName())) {
					token = c.getValue();
					break;
				}
			}
		}

		if (token == null) {
			token = jwtProvider.resolveAccessToken(request);
		}

		if (token == null) {
			throw new JwtAuthenticationException(ErrorCode.JWT_NOT_FOUND);
		}

		if (!jwtProvider.validateAccessToken(token)) {
			throw new JwtAuthenticationException(ErrorCode.INVALID_TYPE_VALUE);
		}

		String blackKey = "blacklist:access:" + token;
		if (Boolean.TRUE.equals(redisTemplate.hasKey(blackKey))) {
			throw new JwtAuthenticationException(ErrorCode.EXPIRED_JWT);
		}

		return token;
	}
}