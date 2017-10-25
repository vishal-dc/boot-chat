package org.vdc.boot.chat;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.vdc.boot.chat.controller.UserController;
import org.vdc.boot.chat.dao.UserRespository;
import org.vdc.boot.chat.domain.LoginForm;
import org.vdc.boot.chat.domain.User;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTests {

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Autowired
	private MockMvc mockMvc;

	private HttpMessageConverter<Object> mappingJackson2HttpMessageConverter;// = new MappingJackson2HttpMessageConverter();

	@MockBean
	UserRespository userRepository;

	@Before
	public void setup() throws Exception {

	}

	@Test
	public void testSuccessfulLogin() throws IOException, Exception {
		LoginForm form = new LoginForm();
		form.setName("Vdc");
		form.setPassword("Vdc");
		User user = new User();
		user.setName("Vdc");

		when(userRepository.findByNameAndPassword(Mockito.any(String.class), Mockito.any(String.class)))
				.thenReturn(user);

		mockMvc.perform(post("/user/Vdc/login").content(this.json(form)).contentType(contentType))
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.name", is("Vdc")));

	}

	@Test
	public void testLoginFailed() throws IOException, Exception {
		LoginForm form = new LoginForm();
		form.setName("Vdc1");
		form.setPassword("Vdc1");

		when(userRepository.findByNameAndPassword(Mockito.any(String.class), Mockito.any(String.class)))
				.thenReturn(null);

		mockMvc.perform(post("/user/Vdc/login").content(this.json(form)).contentType(contentType))
				.andExpect(status().is4xxClientError())
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.status", is("404")))
				.andExpect(jsonPath("$.message", is("User not found!")));
	}

	@Test
	public void testLogout() {
		throw new RuntimeException();

	}

	protected String json(Object o) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();

		this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);

		return mockHttpOutputMessage.getBodyAsString();

	}

	// protected <T> T object(String data, Class<? extends T> clazz ) throws
	// IOException {
	// MockHttpOutputMessage mockHttpOutputMessage = new
	// MockHttpOutputMessage();
	//
	// this.mappingJackson2HttpMessageConverter.write(o,
	// MediaType.APPLICATION_JSON, mockHttpOutputMessage);
	//
	// return mockHttpOutputMessage.getBodyAsString();
	//
	// }

	@SuppressWarnings("unchecked")
	@Autowired
	void setConverters(HttpMessageConverter<?>[] converters) {

		this.mappingJackson2HttpMessageConverter = (HttpMessageConverter<Object>) Arrays.asList(converters).stream()
				.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().orElse(null);

		assertNotNull("the JSON message converter must not be null", this.mappingJackson2HttpMessageConverter);
	}

}
