package com.taeyoon.oauth2login.application;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Controller
@Slf4j
public class ErrorController {

	@GetMapping("/error")
	public ModelAndView error(@RequestParam(value = "errorMessage", required = false) String errorMessage) {
		log.error("error: " + errorMessage);
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("errorMessage", errorMessage);
		return new ModelAndView("error", errorMap);
	}
}
