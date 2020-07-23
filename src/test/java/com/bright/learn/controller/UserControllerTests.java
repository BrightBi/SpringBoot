package com.bright.learn.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTests {
	private MockMvc mvc;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
	}

	@Test
	public void testUserController() throws Exception {
		// 测试UserController
		RequestBuilder request = null;

		// 1、get查一下user列表，应该为空
		request = MockMvcRequestBuilders.get("/users/");
		mvc.perform(request).andExpect(status().isOk()).andExpect(content().string(equalTo("[]")));
		// 2、post提交一个user
		request = MockMvcRequestBuilders.post("/users/").param("id", "1").param("name", "测试");
		mvc.perform(request).andExpect(content().string(equalTo("{\"id\":1,\"name\":\"测试\"}")));
		// 3、get获取user列表，应该有刚才插入的数据
		request = MockMvcRequestBuilders.get("/users/");
		mvc.perform(request).andExpect(status().isOk()).andExpect(content().string(equalTo("[{\"id\":1,\"name\":\"测试\"}]")));
		// 4、put修改id为1的user
		request = MockMvcRequestBuilders.put("/users/1").param("name", "测");
		mvc.perform(request).andExpect(content().string(equalTo("success")));
		// 5、get一个id为1的user
		request = MockMvcRequestBuilders.get("/users/1");
		mvc.perform(request).andExpect(content().string(equalTo("{\"id\":1,\"name\":\"测\"}")));
		// 6、del删除id为1的user
		request = MockMvcRequestBuilders.delete("/users/1");
		mvc.perform(request).andExpect(content().string(equalTo("success")));
		// 7、get查一下user列表，应该为空
		request = MockMvcRequestBuilders.get("/users/");
		mvc.perform(request).andExpect(status().isOk()).andExpect(content().string(equalTo("[]")));
	}
}
