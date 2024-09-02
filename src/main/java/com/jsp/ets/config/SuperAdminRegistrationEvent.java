package com.jsp.ets.config;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.jsp.ets.user.SuperAdmin;
import com.jsp.ets.user.User;
import com.jsp.ets.user.UserRepository;
import com.jsp.ets.user.UserRole;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SuperAdminRegistrationEvent {

	private UserRepository userRepository;

	@Value("${super_admin.email}")
	private String superAdminEmail;

	public SuperAdminRegistrationEvent(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@EventListener(classes = ApplicationReadyEvent.class)
	public void registerSuperAdmin() {

		log.info("Checking if super admin present");
		List<User> superAdmins = userRepository.findByRole(UserRole.SUPER_ADMIN);

		if (superAdmins.isEmpty()) {
			log.info("Super admin is not present. Creating one");
			SuperAdmin superAdmin = new SuperAdmin();
			superAdmin.setRole(UserRole.SUPER_ADMIN);
			superAdmin.setEmail(superAdminEmail);
			superAdmin.setPassword(UUID.randomUUID().toString());
			userRepository.save(superAdmin);
		} else {
			log.info("Super admin present with email: " + superAdmins.getFirst().getEmail());
		}

	}
}
