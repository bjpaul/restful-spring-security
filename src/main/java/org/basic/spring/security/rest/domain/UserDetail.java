package org.basic.spring.security.rest.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class UserDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private String name;
	
	private int age;

	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "user_authentication_id")
	private Authentication authentication;
	
	public UserDetail(){}
	
	public UserDetail(Long id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public UserDetail(String name, int age, String username, String password) {
		this.name = name;
		this.age = age;
		this.authentication = new Authentication(username, password);
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
	
	

	public Authentication getAuthentication() {
		return authentication;
	}

	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
	
	
}
