package com.hasandayan.todoapp.repository;

import java.util.List;

import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.Query;
import org.springframework.stereotype.Repository;

import com.hasandayan.todoapp.dto.TodoDTO;
import com.hasandayan.todoapp.model.TodoItem;

@Repository
public interface TodoItemRepository extends CouchbaseRepository<TodoItem, String> {

	List<TodoItem> findByUserId(String userId);

	TodoItem findByIdAndUserId(String id, String userId);
	
}
