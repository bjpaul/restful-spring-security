package org.basic.spring.security.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.basic.spring.security.rest.domain.UserDetail;
import org.basic.spring.security.rest.dto.entity.user.AuthenticationDto;
import org.basic.spring.security.rest.dto.entity.user.UserDto;
import org.basic.spring.security.rest.dto.entity.user.UserList;
import org.basic.spring.security.rest.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManagedService {
	@Autowired
	private UserDetailRepository userRepository;

	public long count() {
		return userRepository.count();
	}
	
	public UserList findAllUsers() {
		Iterable<UserDetail> users = userRepository.findAll();
		
		if(users == null){
			return new UserList();
		}
		
		List<UserDto> dtos = new ArrayList<UserDto>();
		for (UserDetail user : users) {
			dtos.add(new UserDto(user));
		}
		return new UserList(dtos);
	}

	public UserDto findById(long id) {
		UserDetail user = userRepository.findOne(id);
		UserDto userDto = null;
		if(user != null){
			userDto = new UserDto(user);
		}
		return userDto;
	}

	public void saveUser(AuthenticationDto userDto) {
		userRepository.save(userDto.buildUser());
	}

	public void updateUser(UserDto currentUser, UserDto user) {
		currentUser.setName(user.getName());
        currentUser.setAge(user.getAge());
        userRepository.save(currentUser.buildUser());
	}

	public void deleteUserById(long id) {
		userRepository.delete(id);
	}

	public void deleteAllUsers() {
		userRepository.deleteAll();
	}

}
