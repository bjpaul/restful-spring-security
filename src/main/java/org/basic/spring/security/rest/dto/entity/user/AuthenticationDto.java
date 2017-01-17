package org.basic.spring.security.rest.dto.entity.user;

import org.basic.spring.security.rest.domain.UserDetail;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "name", "age", "username", "password" })
public class AuthenticationDto {
	
    private String name;
	
	private int age;
	
	private String username;
	private String password;
	
	public AuthenticationDto(){}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserDetail buildUser(){
		return new UserDetail(name, age, username, password);
	}

	public UserDto detail(){
		UserDto user = new UserDto();
		user.setAge(this.getAge());
		user.setName(this.getName());
		return user;
	}
}
