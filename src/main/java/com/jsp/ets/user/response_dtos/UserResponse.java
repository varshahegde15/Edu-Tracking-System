package com.jsp.ets.user.response_dtos;

import java.time.LocalDateTime;

import com.jsp.ets.user.UserRole;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

	private String userId;
	private String email;
	private String username;
	private UserRole role;
	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;

}
