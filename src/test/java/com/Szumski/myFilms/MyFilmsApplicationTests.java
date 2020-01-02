package com.Szumski.myFilms;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
class MyFilmsApplicationTests {

	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;


	@Before
	public void setup () {
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		this.mockMvc = builder.build();
	}



	@Test
	void contextLoads() throws Exception {

		MockHttpServletRequestBuilder builder =
				MockMvcRequestBuilders.get("/upcoming")
						.header("testHeader",
								"headerValue")
						.contentType(MediaType.APPLICATION_JSON);
		this.mockMvc.perform(builder)
				.andExpect(MockMvcResultMatchers.status()
						.isOk())
				.andDo(MockMvcResultHandlers.print());
	}

}
