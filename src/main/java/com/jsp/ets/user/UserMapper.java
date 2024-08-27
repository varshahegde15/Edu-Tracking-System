package com.jsp.ets.user;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {

	public User mapToUserEntity(UserRequest userRequest, User user) {
		user.setUsername(userRequest.getUsername());
		user.setEmail(userRequest.getEmail());
		user.setPassword(userRequest.getPassword());
		user.setRole(userRequest.getRole());
		return user;
	}
}
