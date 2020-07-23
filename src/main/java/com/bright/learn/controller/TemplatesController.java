package com.bright.learn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * Spring Boot默认提供静态资源目录位置需置于classpath下，目录名需符合如下规则：
 * /static
 * /public
 * /resources
 * /META-INF/resources
 * 举例：我们可以在src/main/resources/ 目录下创建 static，在该位置放置一个图片文件。
 * 启动程序后，尝试访问http://localhost:8080/D.jpg。如能显示图片，配置成功。
 */
@Controller
public class TemplatesController {
	@RequestMapping("/temp")
	public String index(ModelMap map) {
		// 加入一个属性，用来在模板中读取
		map.addAttribute("host", "http://www.baidu.com");
		// return模板文件的名称，对应src/main/resources/templates/index.html
		return "index";
	}
}
