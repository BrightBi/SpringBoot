package com.bright.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
/**
 * 参考：
 * http://blog.didispace.com/Spring-Boot%E5%9F%BA%E7%A1%80%E6%95%99%E7%A8%8B/
 * http://blog.didispace.com/spring-boot-learning-1x/
 * http://blog.didispace.com/spring-boot-learning-2x/
 * 
 * 
 * 构建 Spring Boot 项目 http://start.spring.io
 * 
 * 默认情况下，Spring Boot的应用主类会自动扫描
 * root package(程序入口类所在的包，比如本项目的 com.bright.learn.Application 所在的包，也可以理解成 main 函数所在的包)
 * 以及所有子包下的所有类来进行初始化。
 * 什么意思呢？举个例子，假设我们将 com.bright.learn.web 包与上面所述的 root package：com.bright.learn 放在同一级，
 * 变成这样：com.bright.web。那么 com.bright.web 包下的用注解注释的组件都不会被扫描加载成 bean（
 * 比如被 @Controller @RestController ... 注解的类都不会被加载成 bean）
 * 
 * 解决办法一 入口类中加 @ComponentScan(basePackages="com.bright") 
 * @SpringBootApplication
 * @ComponentScan(basePackages="com.bright")
 * public class Application {
 *		public static void main(String[] args) {
 *			SpringApplication application = new SpringApplication(Application.class);
 *			application.setAddCommandLineProperties(false);
 *			application.run(args);
 *		}
 *	}
 * 解决办法二 入口类中加 Bean 注解
 * @SpringBootApplication
 * public class Application {
 *		public static void main(String[] args) {
 *			SpringApplication application = new SpringApplication(Application.class);
 *			application.setAddCommandLineProperties(false);
 *			application.run(args);
 *		}
 *		@Bean
 *		public CustomerController customerController() {
 *			return new CustomerController();
 *		}
 *	}
 * 
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		// 正常启动
		// SpringApplication.run(Application.class, args);
		
		/*
		 * 在命令行运行时，连续的两个减号--就是对 application.properties 中的属性值进行赋值的标识。
		 * 所以，java -jar xxx.jar --server.port=8888 命令，等价于我们在 application.properties 中添加属性 server.port=8888，
		 * 通过命令行就能更改应用运行的参数，不安全. 所以 Spring Boot 提供了屏蔽命令行访问属性的设置
		 */
		// 屏蔽命令行访问属性的设置
		SpringApplication application = new SpringApplication(Application.class);
		// 屏蔽命令行参数
		application.setAddCommandLineProperties(false);
		application.run(args);
	}

	/*
	 * Controller 也可以以下面这种方式添加。
	 * 
	 * LocalDate 是 Java8 加进来的，
	 * 使用 Spring Boot 或 Spring Cloud Feign 访问 http://localhost:8080/local-date 时候有可能无法正常处理。
	 * 参考 http://blog.didispace.com/Spring-Boot-And-Feign-Use-localdate/
	 * 
	 * 当前使用的 Spring Boot 2.1.2.RELEASE 可以正常处理。貌似修复了 Spring Boot 1.x.x 中的这个问题。
	 */
	@RestController
	class LocalDateController {
		@PostMapping("/local-date")
		public UserDto user(@RequestBody UserDto userDto) throws Exception {
			return userDto;
		}
	}

	static class UserDto {
		private String name;
		private LocalDate birthday;

		public UserDto() {
		}
		public UserDto(String name, LocalDate birthday) {
			this.name = name;
			this.birthday = birthday;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public LocalDate getBirthday() {
			return birthday;
		}
		public void setBirthday(LocalDate birthday) {
			this.birthday = birthday;
		}
	}
}