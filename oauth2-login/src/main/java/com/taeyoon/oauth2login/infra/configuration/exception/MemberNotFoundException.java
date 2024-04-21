package com.taeyoon.oauth2login.infra.configuration.exception;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.util.Assert;

import lombok.Getter;

@Getter
public class MemberNotFoundException extends AuthenticationException {
	private final OAuth2Error error;

	public MemberNotFoundException(String errorCode) {
		this(new OAuth2Error(errorCode));
	}
	public MemberNotFoundException(OAuth2Error error) {
		this(error, error.getDescription());
	}
	public MemberNotFoundException(OAuth2Error error, Throwable cause) {
		this(error, cause.getMessage(), cause);
	}
	public MemberNotFoundException(OAuth2Error error, String message) {
		this(error, message, null);
	}
	public MemberNotFoundException(OAuth2Error error, String message, Throwable cause) {
		super(message, cause);
		Assert.notNull(error, "error cannot be null");
		this.error = error;
	}
}
