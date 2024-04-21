package com.taeyoon.oauth2login.infra.configuration.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.taeyoon.oauth2login.infra.configuration.handler.SignInFailureHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity(debug = true)
public class SpringSecurity {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(authorize -> authorize
				.requestMatchers("/api/**").authenticated()
				.requestMatchers("/**").permitAll()
			)
			.formLogin(formLogin -> formLogin
				.loginPage("/sign-in")
				.loginProcessingUrl("/sign-in")
				.failureHandler(new SignInFailureHandler( "/sign-in?error"))
			)
			.oauth2Login(oauth2Login -> oauth2Login
				.failureHandler(new SignInFailureHandler( "/sign-in?error"))
			)
		    ;

		return http.build();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web
			.ignoring()
			.requestMatchers("/webjars/**")
			.requestMatchers("/assets/**")
			.requestMatchers("/vendors/**")
			.requestMatchers("/phoenix/**")
			.requestMatchers(
				PathRequest.toStaticResources().atCommonLocations()
			);
	}


	// Resource Owner in memory
	@Bean
	public UserDetailsService userDetailsService() {
		// @formatter:off
		UserDetails userDetails = User.withDefaultPasswordEncoder()
			.username("user")
			.password("password")
			.roles("USER")
			.build();
		// @formatter:on

		return new InMemoryUserDetailsManager(userDetails);
	}

}

