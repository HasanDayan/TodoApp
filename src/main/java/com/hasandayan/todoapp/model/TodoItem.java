package com.hasandayan.todoapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;

@Document
public class TodoItem {

	@Id
	@GeneratedValue(strategy = GenerationStrategy.UNIQUE)
	private String id;

	@Field
	private String content;

	@Field
	private boolean active = false;
	
	@Field
	private String userId;

	public TodoItem() {
	}

	public TodoItem(String id, String content, boolean active, String userId) {
		this.id = id;
		this.content = content;
		this.active = active;
		this.userId = userId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TodoItem [id=").append(id).append(", content=").append(content).append(", active=")
				.append(active).append(", userId=").append(userId).append("]");
		return builder.toString();
	}

}
