package org.basic.spring.security.rest.dto.entity.user;

import org.basic.spring.security.rest.domain.UserDetail;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class UserDto {

	@JsonIgnore
	private long id;
	
    private String name;
	
	private int age;

	public UserDto(){}
	
	public UserDto(UserDetail user){
		this.age = user.getAge();
		this.name = user.getName();
		this.id = user.getId();
	}

	
	public long getId() {
		return id;
	}

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
	
	public UserDetail buildUser(){
		return new UserDetail(id,name, age);
	}

	
}
