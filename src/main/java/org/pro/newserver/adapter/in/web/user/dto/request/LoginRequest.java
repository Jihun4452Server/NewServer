package org.pro.newserver.adapter.in.web.user.dto.request;

public record LoginRequest(
	String email,
	String password
) {}
