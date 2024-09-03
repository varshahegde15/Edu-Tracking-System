package com.jsp.ets.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User, String> {

	List<User> findByRole(UserRole role);
}
