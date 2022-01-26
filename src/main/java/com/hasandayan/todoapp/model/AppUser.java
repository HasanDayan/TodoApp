package com.hasandayan.todoapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;

@Document
public class AppUser {

	@Id
	@GeneratedValue(strategy = GenerationStrategy.UNIQUE)
	private String id;

	@Field
	private String name;

	@Field
	private String surname;

	@Field
	private String username;

	@Field
	private String email;

	@Field
	private String password;

	public AppUser() {

	}

	public AppUser(String id, String name, String surname, String username, String email, String password) {
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
		builder.append("AppUser [id=").append(id).append(", name=").append(name).append(", surname=")
				.append(surname).append(", username=").append(username).append(", email=").append(email)
				.append(", password=").append(password).append("]");
		return builder.toString();
	}

}