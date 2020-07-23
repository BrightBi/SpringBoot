package com.bright.learn;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/*
 * 访问路径 http://localhost:8080/swagger-ui.html 查看结果
 * basePackage("com.bright.learn.controller") 指定扫描的包路径。
 * Swagger 会扫描该包下所有 Controller 定义的 API，并产生文档内容（除了被 @ApiIgnore 指定的请求）。
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.bright.learn.controller")).paths(PathSelectors.any()).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Bright Swagger2 构建 RESTful APIs")
				.description("Description for the page")
				.termsOfServiceUrl("http://www.baidu.com/")
				.version("0.0").build();
	}
}
