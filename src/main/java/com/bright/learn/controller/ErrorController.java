package com.bright.learn.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bright.learn.util.MyException;

@RestController
public class ErrorController {
	//浏览器访问 http://localhost:8080/exception 就可以访问
	@RequestMapping("/exception")
	public String exception() throws Exception {
		throw new Exception("Exception");
	}

	//浏览器访问 http://localhost:8080/null-pointer 就可以访问
	@RequestMapping("/null-pointer")
	public String nullPoint() throws Exception {
		throw new NullPointerException("NullPointer");
	}

	//浏览器访问 http://localhost:8080/illegal-argument 就可以访问
	@RequestMapping("/illegal-argument")
	public String illegalArgument() throws Exception {
		throw new IllegalArgumentException("IllegalArgument");
	}

	//浏览器访问 http://localhost:8080/json-exception 就可以访问
	@RequestMapping("/json-exception")
	public String jsonException() throws Exception {
		throw new MyException("MyException");
	}
}
