package com.taeyoon.oauth2login.infra.configuration.security;

import static org.springframework.security.config.Customizer.*;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

import com.taeyoon.oauth2login.infra.configuration.handler.OAuth2LoginFailureHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurity {
	private final OAuth2LoginFailureHandler oAuth2LoginFailureHandler;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(authorize -> authorize
				.requestMatchers("/api/**").authenticated()
				.requestMatchers("/**").permitAll()
			)
			.formLogin(withDefaults())
			.oauth2Login(oauth2Login -> oauth2Login.failureHandler(oAuth2LoginFailureHandler));

		return http.build();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web
			.ignoring()
			.requestMatchers("/webjars/**")
			.requestMatchers("/assets/**")
			.requestMatchers(
				PathRequest.toStaticResources().atCommonLocations()
			);
	}

}

