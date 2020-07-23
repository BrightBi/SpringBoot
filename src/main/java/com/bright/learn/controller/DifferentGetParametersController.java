package com.bright.learn.controller;

import com.bright.learn.domain.User;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DifferentGetParametersController {
	/*
	 *  Postman http://localhost:8080/request-body 就可以访问
	 *  
	 *  Postman 配置：
	 *  Body:
	 *      {"id":77,"name":"1987-11-28"}
	 *  Headers:
	 *      Content-Type: application/json;charset=utf-8
	 */
	@PostMapping("/request-body")
	public User dateTimeFormat(@RequestBody User user) {
		System.out.println("DateAndTimeFormatting dateTimeFormat.");
		return user;
	}

	/*
	 *  Postman http://localhost:8080/model-attribute?id=99&name=my-name 就可以访问
	 *  
	 *  Postman 配置：
	 *  Params:
	 *      id: 99
	 *      name: my-name
	 *  Headers:
	 *      Content-Type: application/json;charset=utf-8
	 */
	@PostMapping("/model-attribute")
	public User modelAttribute(@ModelAttribute User user) {
		System.out.println("DateAndTimeFormatting modelAttribute.");
		return user;
	}
	
	/*
	 *  Postman http://localhost:8080/request-param?info=my-info&age=33 就可以访问
	 *  
	 *  Postman 配置：
	 *  Params:
	 *      info: my-info
	 *      age: 33
	 *  Headers:
	 *      Content-Type: application/json;charset=utf-8
	 */
	@PostMapping("/request-param")
	public String requestParam(@RequestParam("info") String info, @RequestParam("age") int age) {
		System.out.println("DateAndTimeFormatting requestParam." + info + age);
		return info + age;
	}
}
