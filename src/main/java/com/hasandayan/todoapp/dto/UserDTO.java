package com.hasandayan.todoapp.dto;

public class UserDTO {

	private String id;

	private String accessToken;

	private String name;

	private String surname;

	private String username;

	private String email;

	private String password;

	public UserDTO() {

	}

	public UserDTO(String id, String name, String surname, String username, String email, String password) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserDTO [id=").append(id).append(", accessToken=").append(accessToken).append(", name=")
				.append(name).append(", surname=").append(surname).append(", username=").append(username)
				.append(", email=").append(email).append(", password=").append(password).append("]");
		return builder.toString();
	}

	

}
