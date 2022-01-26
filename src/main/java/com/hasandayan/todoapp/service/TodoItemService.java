package com.hasandayan.todoapp.service;

import java.util.List;

import com.hasandayan.todoapp.dto.TodoDTO;

public interface TodoItemService {

	TodoDTO save(TodoDTO todoDTO);

	List<TodoDTO> findAll(String jwtToken);

	TodoDTO update(String todoId, String jwtToken, TodoDTO todoDTO);

	void delete(String todoId, String jwtToken);

}
