package com.bright.learn.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/*
 * @Controller：修饰class，用来创建处理 http 请求的对象
 * @RestController：Spring4 之后加入的注解，原来在 @Controller 中返回 json 需要 @ResponseBody 来配合，
 * 如果直接用 @RestController 替代 @Controller 就不需要再配置 @ResponseBody，默认返回 json 格式。
 * 
 * @RequestMapping 标签用来配置 url 映射。
 * @RequestMapping(value="/my") 配置在类上，意味着类中的方法上的映射都在 /my 下
 */
@RestController
@RequestMapping(value="/my")
public class HelloWorldController {
	
	@Value("${com.bright.test.desc}")
    private String desc;
	@Value("${com.bright.test.value}")
    private String value;
	@Value("${com.bright.test.number}")
    private int number;
	@Value("${com.bright.test.long}")
    private long longValue;
	@Value("${com.bright.test.less10}")
    private int less10;
	@Value("${com.bright.test.10to20}")
    private int between;
	@Value("${com.bright.test.defaultValue:default}")
    private String defaultValue;
	@Value("${com.bright.test.null}")
    private String isnull;
	// 浏览器访问 http://localhost:8080/my/hello 就可以访问
	@RequestMapping("/hello")
	public String index() {
		String result = "Hello World! " + desc + " | " + value + " | " + number + " | " + longValue + " | " + less10 + " | " + between;
		System.out.println(result);
		if (defaultValue == null) {
			System.out.println("defaultValue is null");
		} else {
			System.out.println("defaultValue is :" + defaultValue);
			System.out.println("length is :" + defaultValue.length());
		}
		if (isnull == null) {
			System.out.println("isnull is null");
		} else {
			System.out.println("isnull is :" + isnull);
		}
		System.out.println(result);
		return "Hello World! ";
	}
}
