package com.hasandayan.todoapp;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hasandayan.todoapp.controller.AppUserController;
import com.hasandayan.todoapp.dto.UserDTO;
import com.hasandayan.todoapp.service.AppUserService;
import com.hasandayan.todoapp.util.JwtTokenUtil;

@WebMvcTest(AppUserController.class)
class AppUserControllerTest {

	@MockBean
	private AuthenticationManager authenticationManager;

	@MockBean
	private JwtTokenUtil jwtTokenUtil;
	
	@MockBean
	private AuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AppUserService appUserService;

	@Test
	void postUserTest() throws Exception {

		UserDTO dto = new UserDTO();
		dto.setName("hasan");
		dto.setUsername("hdayan");
		dto.setPassword("123456");
		dto.setEmail("hdayan@bc.com");

		ObjectMapper mapper = new ObjectMapper();
		String requestJson = mapper.writeValueAsString(dto);

		when(appUserService.existsByUsername("hdayan")).thenReturn(false);
		when(appUserService.existsByEmail("hdayan@bc.com")).thenReturn(false);
		when(appUserService.save(dto)).thenReturn(new UserDTO());

		mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/signup").contentType(MediaType.APPLICATION_JSON)
				.content(requestJson))
				.andDo(print())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Registration successful"))
				.andExpect(status().isCreated());
	}

}
