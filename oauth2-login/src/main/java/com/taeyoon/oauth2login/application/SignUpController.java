package com.taeyoon.oauth2login.application;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Controller
@Slf4j
public class SignUpController {

	@GetMapping(value="/sign-up")
	public String signUp(@RequestParam(required = false) String errorCode) {

		log.info("errorCode: {}", errorCode);
		return "signUp";
	}
}
