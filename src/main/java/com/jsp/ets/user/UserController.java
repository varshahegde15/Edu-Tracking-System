package com.jsp.ets.user;

import com.jsp.ets.user.request_dtos.*;
import com.jsp.ets.utility.ErrorStructure;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.ets.user.response_dtos.StudentResponseDTO;
import com.jsp.ets.user.response_dtos.TrainerResponseDTO;
import com.jsp.ets.user.response_dtos.UserResponse;
import com.jsp.ets.utility.CustomResponseBuilder;
import com.jsp.ets.utility.ResponseStructure;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@Tag(name = "User Controller", description = "APIs for managing User.")
public class UserController {

	private UserService userService;
	private CustomResponseBuilder responseBuilder;



	@Operation(description = "The API endpoint is used to register a new Admin user into the second level cache."
			+ " The endpoint validates the registration details and assigns the ADMIN role to the user.",
			responses = {
					@ApiResponse(responseCode = "201", description = "Admin created"),
					@ApiResponse(responseCode = "400", description = "Bad Request, invalid inputs", content = @Content(schema = @Schema(anyOf = ErrorStructure.class))),
					@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(anyOf = RuntimeException.class)))
			})
	@PostMapping("/register/admins")
	public ResponseEntity<ResponseStructure<UserResponse>> registerAdmin(
			@RequestBody @Valid RegistrationRequestDTO registrationRequestDTO) throws MessagingException {
		UserResponse response = userService.registerUser(registrationRequestDTO, UserRole.ADMIN);
		return responseBuilder.success(HttpStatus.ACCEPTED, "Accepted the details, verify your email by submitting the otp", response);
	}





	@Operation(description = "The API endpoint is used to register a new HR into the second level cache."
			+ " The endpoint validates the registration details and assigns HR role to the user.",
			responses = {
					@ApiResponse(responseCode = "201", description = "HR created"),
					@ApiResponse(responseCode = "400", description = "Bad Request, invalid inputs", content = @Content(schema = @Schema(anyOf = ErrorStructure.class))),
					@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(anyOf = RuntimeException.class)))
			})
	@PostMapping("/register/hrs")
	public ResponseEntity<ResponseStructure<UserResponse>> registerHR(
			@RequestBody @Valid RegistrationRequestDTO registrationRequestDTO) throws MessagingException {
		UserResponse response = userService.registerUser(registrationRequestDTO, UserRole.HR);
		return responseBuilder.success(HttpStatus.ACCEPTED, "Accepted the details, verify your email by submitting the otp", response);
	}





	@Operation(description = "The API endpoint is used to register a new Trainer into the second level cache."
			+ " The endpoint validates the registration details and assigns Trainer role to the user.",
			responses = {
					@ApiResponse(responseCode = "201", description = "Trainer created"),
					@ApiResponse(responseCode = "400", description = "Bad Request, invalid inputs", content = @Content(schema = @Schema(anyOf = ErrorStructure.class))),
					@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(anyOf = RuntimeException.class)))
			})
	@PostMapping("/register/trainers")
	public ResponseEntity<ResponseStructure<UserResponse>> registerTrainer(
			@RequestBody @Valid RegistrationRequestDTO registrationRequestDTO) throws MessagingException {
		UserResponse response = userService.registerUser(registrationRequestDTO, UserRole.TRAINER);
		return responseBuilder.success(HttpStatus.ACCEPTED, "Accepted the details, verify your email by submitting the otp", response);
	}





	@Operation(description = "The API endpoint is used to register a new Student into the second level cache."
			+ " The endpoint validates the registration details and assigns Student role to the user.",
			responses = {
					@ApiResponse(responseCode = "201", description = "HR created"),
					@ApiResponse(responseCode = "400", description = "Bad Request, invalid inputs", content = @Content(schema = @Schema(anyOf = ErrorStructure.class))),
					@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(anyOf = RuntimeException.class)))
			})
	@PostMapping("/register/students")
	public ResponseEntity<ResponseStructure<UserResponse>> registerStudent(
			@RequestBody @Valid RegistrationRequestDTO registrationRequestDTO) throws MessagingException {
		UserResponse response = userService.registerUser(registrationRequestDTO, UserRole.STUDENT);
		return responseBuilder.success(HttpStatus.ACCEPTED, "Accepted the details, verify your email by submitting the otp", response);
	}




	@Operation(description = "The API endpoint is used to verify email and otp."
			+ " And registers the user to the database after successful validation",
			responses = {
					@ApiResponse(responseCode = "200", description = "User registered Successfully"),
					@ApiResponse(responseCode = "400", description = "Bad Request, invalid inputs", content = @Content(schema = @Schema(anyOf = ErrorStructure.class))),
			})
	@PostMapping("/users/verify")
	public ResponseEntity<ResponseStructure<UserResponse>> verifyUser(
			@RequestBody @Valid OtpRequestDto otpRequestDto) {
		UserResponse response = userService.verifyUser(otpRequestDto);
		return responseBuilder.success(HttpStatus.OK, "Successfully registered the user", response);
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

	@PostMapping("/login")
	public String login(@RequestBody @Valid LoginRequestDTO loginRequestDTO){
		return userService.login(loginRequestDTO);
	}

}
