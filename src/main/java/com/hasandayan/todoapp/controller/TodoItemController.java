package com.hasandayan.todoapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hasandayan.todoapp.dto.ResponseDTO;
import com.hasandayan.todoapp.dto.TodoDTO;
import com.hasandayan.todoapp.service.TodoItemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "TodoItemController", description = "Todo Item Api Documentation")
@SecurityRequirement(name = "JWT")
@RequestMapping("/api")
public class TodoItemController {

	@Autowired
	private TodoItemService todoItemService;
	
	@GetMapping("/todoItems")
	@Operation(summary = "List to-do items")
	public ResponseEntity<List<TodoDTO>> getTodoItems(HttpServletRequest request) {
		return ResponseEntity.ok(todoItemService.findAll(request.getHeader("Authorization")));
	}
	
	@PostMapping("/todoItem")
	@Operation(summary = "Save to-do item method")
	public ResponseEntity<ResponseDTO> postTodoItem(@RequestBody TodoDTO todoDTO) {
		todoItemService.save(todoDTO);
		return new ResponseEntity<>(new ResponseDTO("Success"), HttpStatus.OK);
	}
	
	@PutMapping("/todoItem/{todoId}")
	@Operation(summary = "Update to-do item method")
	public ResponseEntity<ResponseDTO> putTodoItem(@PathVariable String todoId, @RequestBody TodoDTO todoDTO, HttpServletRequest request) {
		todoItemService.update(todoId, request.getHeader("Authorization"), todoDTO);
		return new ResponseEntity<>(new ResponseDTO("Success"), HttpStatus.OK);
	}
	
	@DeleteMapping("/todoItem/{todoId}")
	@Operation(summary = "Delete to-do items")
	public ResponseEntity<String> deleteTodoItem(@PathVariable String todoId, HttpServletRequest request) {
		todoItemService.delete(todoId, request.getHeader("Authorization"));
		return ResponseEntity.ok("Success");
	}

}
