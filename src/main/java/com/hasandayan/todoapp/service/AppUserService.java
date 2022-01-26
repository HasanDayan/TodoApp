package com.hasandayan.todoapp.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.hasandayan.todoapp.dto.UserDTO;

public interface AppUserService extends UserDetailsService {

	UserDTO findByUsername(String username);

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

	UserDTO save(UserDTO userDTO);

}
