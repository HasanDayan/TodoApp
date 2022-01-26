package com.hasandayan.todoapp.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hasandayan.todoapp.dto.UserDTO;
import com.hasandayan.todoapp.model.AppUser;
import com.hasandayan.todoapp.repository.AppUserRepository;
import com.hasandayan.todoapp.service.AppUserService;

@Service
public class AppUserServiceImpl implements AppUserService {

	@Autowired
	private AppUserRepository appUserRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		AppUser user = appUserRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
	}

	@Override
	public UserDTO findByUsername(String username) throws UsernameNotFoundException {
		AppUser user = appUserRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return convertToDTO(user);
	}

	@Override
	public boolean existsByUsername(String username) {
		return appUserRepository.findByUsername(username).isPresent();
	}

	@Override
	public boolean existsByEmail(String email) {
		return appUserRepository.findByEmail(email).isPresent();
	}

	@Override
	public UserDTO save(UserDTO userDTO) {
		return convertToDTO(appUserRepository.save(convertToItem(userDTO)));
	}

	private UserDTO convertToDTO(AppUser item) {
		return new UserDTO(item.getId(), item.getName(), item.getSurname(), item.getUsername(), item.getEmail(),
				item.getPassword());
	}

	private AppUser convertToItem(UserDTO dto) {
		return new AppUser(dto.getId(), dto.getName(), dto.getSurname(), dto.getUsername(), dto.getEmail(),
				encoder.encode(dto.getPassword()));
	}

}
