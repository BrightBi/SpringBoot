package com.bright.learn.controller;

import com.bright.learn.domain.XmlUser;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 
 * @author mingliangbi
 *
 */
@Controller
@RequestMapping(value="/xml")
public class XmlController {
	
	/*
	 *  Postman http://localhost:8080/xml/xml-user 就可以访问
	 *  
	 *  Postman 配置：
	 *  Body:
	 *      <XmlUser>
	 *          <name>Bright</name>
	 *          <age>10</age>
	 *      </XmlUser>
	 *  Headers:
	 *      Content-Type: application/xml;charset=utf-8
	 */
	@PostMapping(value = "/xml-user", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
	@ResponseBody
	public XmlUser create(@RequestBody XmlUser user) {
		user.setName("xml : " + user.getName());
		user.setAge(user.getAge() + 100);
		System.out.println(user.getName() + "|" + user.getAge());
		return user;
	}
}