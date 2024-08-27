package com.jsp.ets.user;

import java.time.LocalDateTime;

import com.jsp.ets.config.GenerateSequenceId;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

	@Id
	@GenerateSequenceId
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "user_name")
	private String username;
	
	@Column(name = "user_email")
	private String email;
	
	@Column(name = "user_password")
	private String password;
	
	@Column(name = "user_role")
	@Enumerated(EnumType.STRING)
	private UserRole role;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@Column(name = "modified_at")
	private LocalDateTime modifiedAt;
	
}
