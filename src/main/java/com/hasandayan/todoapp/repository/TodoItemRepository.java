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

	@Query("update `todo` set active = :active WHERE id = :id AND userId = :userId")
	TodoItem update(String id, String userId, boolean active);

//	@Query("delete from `todo` WHERE id = :id AND userId = :userId")
	@Query("#{#n1ql.delete} WHERE #{#n1ql.filter} AND id = $1 AND userId = $2 #{#n1ql.returning}")
	void deleteByIdAndUserId(String id, String userId);

	TodoItem findByIdAndUserId(String id, String userId);
	
}
