package com.taeyoon.oauth2login.application;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Controller
@Slf4j
public class SignInController {

	@GetMapping(value="/sign-in")
	public String signIn(Model model, @RequestParam(required = false) String errorMessage) {

		if (errorMessage != null) {
			model.addAttribute("errorMessage", errorMessage);
			model.addAttribute("isAuthenticationException", true);
		} else {
			model.addAttribute("isAuthenticationException", false);
		}
		return "signIn";
	}

	@PostMapping(value="/sign-in")
	public String signInProcess(Model model) {

		return "index";
	}
}
