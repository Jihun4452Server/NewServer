package org.pro.newserver.application.user.dto;

import org.pro.newserver.domain.user.model.Gender;

public record UserCommand(String name, String email, String password, Gender gender) {}
