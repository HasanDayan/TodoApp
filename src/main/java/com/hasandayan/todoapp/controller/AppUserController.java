package com.hasandayan.todoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hasandayan.todoapp.dto.ResponseDTO;
import com.hasandayan.todoapp.dto.UserDTO;
import com.hasandayan.todoapp.service.AppUserService;
import com.hasandayan.todoapp.util.JwtTokenUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "AppUserController", description = "Application User Api Documentation")
@RequestMapping("/api/auth")
public class AppUserController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private AppUserService appUserService;

	@PostMapping("/signup")
	@Operation(summary = "Signup user method")
	public ResponseEntity<ResponseDTO> postUser(@RequestBody UserDTO userDTO) {

		if (appUserService.existsByUsername(userDTO.getUsername())) {
			return ResponseEntity.badRequest().body(new ResponseDTO("Username is already taken!"));
		}

		if (appUserService.existsByEmail(userDTO.getEmail())) {
			return ResponseEntity.badRequest().body(new ResponseDTO("Email is already in use!"));
		}

		appUserService.save(userDTO);

		return new ResponseEntity<>(new ResponseDTO("Registration successful"), HttpStatus.CREATED);
	}

	@PostMapping("/signin")
	@Operation(summary = "Login user method")
	public ResponseEntity<UserDTO> getUsers(@RequestBody UserDTO userDTO) {

		authenticate(userDTO.getUsername(), userDTO.getPassword());

		UserDTO usetDTO = appUserService.findByUsername(userDTO.getUsername());

		String token = jwtTokenUtil.generateTokenForUserDetails(usetDTO.getUsername());

		usetDTO.setAccessToken(token);

		return new ResponseEntity<>(usetDTO, HttpStatus.ACCEPTED);
	}

	private void authenticate(String username, String password) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	}

}
