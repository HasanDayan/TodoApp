package com.hasandayan.todoapp.dto;

public class TodoDTO {
	
	private String id;

	private String content;

	private boolean active = false;

	private String userId;
	
	public TodoDTO() {
		
	}

	public TodoDTO(String id, String content, boolean active, String userId) {
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
		builder.append("TodoDTO [id=").append(id).append(", content=").append(content).append(", active=")
				.append(active).append(", userId=").append(userId).append("]");
		return builder.toString();
	}

}
