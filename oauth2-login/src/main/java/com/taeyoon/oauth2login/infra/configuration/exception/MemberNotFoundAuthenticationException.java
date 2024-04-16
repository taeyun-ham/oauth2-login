package com.taeyoon.oauth2login.infra.configuration.exception;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.util.Assert;

import lombok.Getter;

@Getter
public class MemberNotFoundAuthenticationException extends AuthenticationException {
	private final OAuth2Error error;

	public MemberNotFoundAuthenticationException(String errorCode) {
		this(new OAuth2Error(errorCode));
	}
	public MemberNotFoundAuthenticationException(OAuth2Error error) {
		this(error, error.getDescription());
	}
	public MemberNotFoundAuthenticationException(OAuth2Error error, Throwable cause) {
		this(error, cause.getMessage(), cause);
	}
	public MemberNotFoundAuthenticationException(OAuth2Error error, String message) {
		this(error, message, null);
	}
	public MemberNotFoundAuthenticationException(OAuth2Error error, String message, Throwable cause) {
		super(message, cause);
		Assert.notNull(error, "error cannot be null");
		this.error = error;
	}
}
