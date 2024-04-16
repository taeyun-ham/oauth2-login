package com.taeyoon.oauth2login.infra.configuration.handler;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.taeyoon.oauth2login.infra.configuration.exception.MemberNotFoundAuthenticationException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OAuth2LoginFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
		AuthenticationException exception) throws
		IOException, ServletException {
		//-- 로그인이 실패했을 때 작동되는 로직 입력 --//
		//-- 회원가입으로 유도 할 경우 redirect --//
		log.error("==================================, {}", exception.getMessage());
		if (exception instanceof MemberNotFoundAuthenticationException ex) {
			response.sendRedirect("/sign-up?errorCode=" + ex.getError().getErrorCode());
		}
	}
}
