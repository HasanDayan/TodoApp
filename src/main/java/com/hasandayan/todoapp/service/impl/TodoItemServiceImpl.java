package com.hasandayan.todoapp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hasandayan.todoapp.dto.TodoDTO;
import com.hasandayan.todoapp.dto.UserDTO;
import com.hasandayan.todoapp.model.TodoItem;
import com.hasandayan.todoapp.repository.TodoItemRepository;
import com.hasandayan.todoapp.service.AppUserService;
import com.hasandayan.todoapp.service.TodoItemService;
import com.hasandayan.todoapp.util.JwtTokenUtil;

@Service
public class TodoItemServiceImpl implements TodoItemService {

	@Autowired
	private TodoItemRepository todoItemRepository;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private AppUserService appUserService;

	@Override
	public TodoDTO save(TodoDTO todoDTO) {
		return convertToItem(todoItemRepository.save(convertToDTO(todoDTO)));
	}

	@Override
	public List<TodoDTO> findAll(String jwtToken) {
		UserDTO loginUser = getLoginUser(jwtToken);

		return todoItemRepository.findByUserId(loginUser.getId()).stream().map(this::convertToItem)
				.collect(Collectors.toList());
	}

	@Override
	public TodoDTO update(String todoId, String jwtToken, TodoDTO todoDTO) {

		UserDTO userDTO = getLoginUser(jwtToken);
		
		TodoItem todoItem = todoItemRepository.findByIdAndUserId(todoId, userDTO.getId());
		todoItem.setActive(todoDTO.isActive());

		return convertToItem(todoItemRepository.save(todoItem));
	}

	@Override
	public void delete(String todoId, String jwtToken) {
		UserDTO userDTO = getLoginUser(jwtToken);

		TodoItem todoItem = todoItemRepository.findByIdAndUserId(todoId, userDTO.getId());
		
		todoItemRepository.deleteById(todoItem.getId());
	}

	private UserDTO getLoginUser(String jwtToken) {

		if (jwtToken != null && jwtToken.startsWith("Bearer ")) {
			jwtToken = jwtToken.substring(7);

			String username = jwtTokenUtil.retrieveUsernameFromToken(jwtToken);

			return appUserService.findByUsername(username);
		}

		return new UserDTO();
	}

	private TodoItem convertToDTO(TodoDTO dto) {
		return new TodoItem(dto.getId(), dto.getContent(), dto.isActive(), dto.getUserId());
	}

	private TodoDTO convertToItem(TodoItem item) {
		return new TodoDTO(item.getId(), item.getContent(), item.isActive(), item.getUserId());
	}

}
