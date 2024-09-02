package com.jsp.ets.mapping;

import org.springframework.stereotype.Component;

import com.jsp.ets.user.Student;
import com.jsp.ets.user.Trainer;
import com.jsp.ets.user.User;
import com.jsp.ets.user.request_dtos.RegistrationRequestDTO;
import com.jsp.ets.user.request_dtos.StudentRequestDTO;
import com.jsp.ets.user.request_dtos.TrainerRequestDTO;
import com.jsp.ets.user.response_dtos.StudentResponseDTO;
import com.jsp.ets.user.response_dtos.TrainerResponseDTO;
import com.jsp.ets.user.response_dtos.UserResponse;

@Component
public class UserMapper {

	public User mapToUserEntity(RegistrationRequestDTO registrationRequestDTO, User user) {
		user.setUsername(registrationRequestDTO.getUsername());
		user.setEmail(registrationRequestDTO.getEmail());
		user.setPassword(registrationRequestDTO.getPassword());
		return user;
	}

	public Student mapToStudentEntity(StudentRequestDTO studentRequestDTO, Student student) {
		student.setUsername(studentRequestDTO.getUsername());
		student.setEmail(studentRequestDTO.getEmail());
		student.setDegree(studentRequestDTO.getDegree());
		student.setStream(studentRequestDTO.getStream());
		student.setYop(studentRequestDTO.getYop());
		student.setDegreePercentage(studentRequestDTO.getDegreePercentage());
		student.setTenthPercentage(studentRequestDTO.getTenthPercentage());
		student.setTenthPercentage(studentRequestDTO.getTenthPercentage());
		student.setTwelvethPercentage(studentRequestDTO.getTwelvethPercentage());
		return student;
	}

	public Trainer mapToTrainerEntity(TrainerRequestDTO trainerRequestDTO, Trainer trainer) {
		trainer.setUsername(trainerRequestDTO.getUsername());
		trainer.setEmail(trainerRequestDTO.getEmail());
		trainer.setSubjects(trainerRequestDTO.getSubjects());

		return trainer;
	}

	public UserResponse mapToUserResponse(User user) {
		UserResponse response = new UserResponse();
		response.setUserId(user.getUserId());
		response.setEmail(user.getEmail());
		response.setUsername(user.getUsername());
		response.setCreatedAt(user.getCreatedAt());
		response.setModifiedAt(user.getModifiedAt());
		response.setRole(user.getRole());
		return response;
	}

	public StudentResponseDTO mapToStudentResponse(Student student) {
		StudentResponseDTO response = new StudentResponseDTO();
		response.setUserId(student.getUserId());
		response.setUsername(student.getUsername());
		response.setEmail(student.getEmail());
		response.setDegree(student.getDegree());
		response.setStream(student.getStream());
		response.setYop(student.getYop());
		response.setDegreePercentage(student.getDegreePercentage());
		response.setTenthPercentage(student.getTenthPercentage());
		response.setTenthPercentage(student.getTenthPercentage());
		response.setTwelvethPercentage(student.getTwelvethPercentage());
		response.setCreatedAt(student.getCreatedAt());
		response.setModifiedAt(student.getModifiedAt());
		response.setRole(student.getRole());

		return response;
	}

	public TrainerResponseDTO mapToTrainerResponse(Trainer trainer) {
		TrainerResponseDTO response = new TrainerResponseDTO();
		response.setUserId(trainer.getUserId());
		response.setUsername(trainer.getUsername());
		response.setEmail(trainer.getEmail());
		response.setSubjects(trainer.getSubjects());

		return response;
	}

}
