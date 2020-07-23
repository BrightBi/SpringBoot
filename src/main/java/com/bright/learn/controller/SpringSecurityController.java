package com.bright.learn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/security")
public class SpringSecurityController {
	// http://localhost:8080/security/
	@RequestMapping("/")
	public String index() {
		return "indexSecurity";
	}

	// http://localhost:8080/security/helloSecurity
	@RequestMapping("/helloSecurity")
	public String hello() {
		return "hi";
	}

	// http://localhost:8080/security/login
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
}
