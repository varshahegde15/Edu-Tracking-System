package com.jsp.ets.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.ets.user.request_dtos.RegistrationRequestDTO;
import com.jsp.ets.user.request_dtos.StudentRequestDTO;
import com.jsp.ets.user.request_dtos.TrainerRequestDTO;
import com.jsp.ets.user.response_dtos.StudentResponseDTO;
import com.jsp.ets.user.response_dtos.TrainerResponseDTO;
import com.jsp.ets.user.response_dtos.UserResponse;
import com.jsp.ets.utility.CustomResponseBuilder;
import com.jsp.ets.utility.ResponseStructure;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UserController {

	private UserService userService;
	private CustomResponseBuilder responseBuilder;

	@PostMapping("/admins/register")
	public ResponseEntity<ResponseStructure<UserResponse>> saveAdmin(
			@RequestBody @Valid RegistrationRequestDTO registrationRequestDTO) {
		UserResponse response = userService.registerUser(registrationRequestDTO, UserRole.ADMIN);
		return responseBuilder.success(HttpStatus.CREATED, "Admin created", response);
	}

	@PostMapping("/hrs/register")
	public ResponseEntity<ResponseStructure<UserResponse>> saveHR(
			@RequestBody @Valid RegistrationRequestDTO registrationRequestDTO) {
		UserResponse response = userService.registerUser(registrationRequestDTO, UserRole.HR);
		return responseBuilder.success(HttpStatus.CREATED, "HR created", response);
	}

	@PostMapping("/trainers/register")
	public ResponseEntity<ResponseStructure<UserResponse>> saveTrainer(
			@RequestBody @Valid RegistrationRequestDTO registrationRequestDTO) {
		UserResponse response = userService.registerUser(registrationRequestDTO, UserRole.TRAINER);
		return responseBuilder.success(HttpStatus.CREATED, "Trainer created", response);
	}

	@PostMapping("/students/register")
	public ResponseEntity<ResponseStructure<UserResponse>> saveStudent(
			@RequestBody @Valid RegistrationRequestDTO registrationRequestDTO) {
		UserResponse response = userService.registerUser(registrationRequestDTO, UserRole.STUDENT);
		return responseBuilder.success(HttpStatus.CREATED, "Student created", response);
	}

	@PutMapping("/students/{studentId}")
	public ResponseEntity<ResponseStructure<StudentResponseDTO>> updateStudent(
			@RequestBody @Valid StudentRequestDTO studentRequestDTO, @PathVariable String studentId) {
		StudentResponseDTO studentResponseDTO = (StudentResponseDTO) userService.updateUser(studentRequestDTO,
				studentId);
		return responseBuilder.success(HttpStatus.OK, "Student updated", studentResponseDTO);
	}

	@PatchMapping("/students/{studentId}")
	public ResponseEntity<ResponseStructure<StudentResponseDTO>> updateStudentStack(@RequestParam @Valid String stack,
			@PathVariable String studentId) {
		StudentResponseDTO studentResponseDTO = userService.updateStudentStack(stack, studentId);
		return responseBuilder.success(HttpStatus.OK, "Student stack updated", studentResponseDTO);
	}

	@PutMapping("/trainers/{trainerId}")
	public ResponseEntity<ResponseStructure<TrainerResponseDTO>> updateTrainer(
			@RequestBody @Valid TrainerRequestDTO trainerRequestDTO, @PathVariable String trainerId) {
		TrainerResponseDTO trainerResponseDTO = (TrainerResponseDTO) userService.updateUser(trainerRequestDTO,
				trainerId);
		return responseBuilder.success(HttpStatus.OK, "Trainer updated", trainerResponseDTO);
	}

}
