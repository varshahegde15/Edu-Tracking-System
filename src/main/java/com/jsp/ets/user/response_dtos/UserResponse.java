package com.jsp.ets.user.response_dtos;

import com.jsp.ets.user.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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
