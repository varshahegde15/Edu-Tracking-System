package com.jsp.ets.user;

import com.jsp.ets.exception.InvalidStackException;
import com.jsp.ets.user.request_dtos.*;
import com.jsp.ets.user.response_dtos.StudentResponseDTO;
import com.jsp.ets.user.response_dtos.TrainerResponseDTO;
import com.jsp.ets.user.response_dtos.UserResponse;
import com.jsp.ets.utility.CustomResponseBuilder;
import com.jsp.ets.utility.ErrorStructure;
import com.jsp.ets.utility.ResponseStructure;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Tag(name = "User Controller", description = "APIs for managing Users including registration, login, and updates.")
public class UserController {

	private UserService userService;
	private CustomResponseBuilder responseBuilder;

	private static final String VERIFY_EMAIL_MESSAGE = "Accepted the details, verify your email by submitting the otp";



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
		return responseBuilder.success(HttpStatus.ACCEPTED, VERIFY_EMAIL_MESSAGE, response);
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
		return responseBuilder.success(HttpStatus.ACCEPTED, VERIFY_EMAIL_MESSAGE, response);
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
		return responseBuilder.success(HttpStatus.ACCEPTED, VERIFY_EMAIL_MESSAGE, response);
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
		return responseBuilder.success(HttpStatus.ACCEPTED, VERIFY_EMAIL_MESSAGE, response);
	}




	@Operation(description = "The API endpoint is used to verify email and otp."
			+ " And registers the user to the database after successful verification",
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





	@Operation(description = "The API endpoint is used to update the details of an existing Student user based on a unique Identifier."
			+ " The endpoint requires the path variable userId and the student details that are to be updated.",
			responses = {
					@ApiResponse(responseCode = "200", description = "Student updated"),
					@ApiResponse(responseCode = "400", description = "Bad Request, invalid inputs", content = @Content(schema = @Schema(anyOf = ErrorStructure.class))),
					@ApiResponse(responseCode = "404", description = "Student not found", content = @Content(schema = @Schema(anyOf = ErrorStructure.class))),
					@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(anyOf = RuntimeException.class)))
			})
	@PutMapping("/students/{studentId}")
	public ResponseEntity<ResponseStructure<StudentResponseDTO>> updateStudent(
			@RequestBody @Valid StudentRequestDTO studentRequestDTO, @PathVariable String studentId) {
		StudentResponseDTO studentResponseDTO = (StudentResponseDTO) userService.updateUser(studentRequestDTO,
				studentId);
		return responseBuilder.success(HttpStatus.OK, "Student updated", studentResponseDTO);
	}






	@Operation(
			summary = "Update Student Stack",
			description = "The API endpoint is used to update the stack of an existing Student user based on a unique Identifier. "
					+ "The endpoint requires the path variable userId and the new stack value. "
					+ "Only the following stack values are allowed: JAVA_FULL_STACK, MERN_STACK, PYTHON_FULL_STACK. "
					+ "If an invalid stack value is provided, an exception will be thrown.",
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "Student stack updated",
							content = @Content(schema = @Schema(implementation = StudentResponseDTO.class))
					),
					@ApiResponse(
							responseCode = "400",
							description = "Invalid stack value provided",
							content = @Content(schema = @Schema(implementation = InvalidStackException.class))
					),
					@ApiResponse(
							responseCode = "404",
							description = "Student not found",
							content = @Content(schema = @Schema(implementation = ErrorStructure.class))
					),
					@ApiResponse(
							responseCode = "500",
							description = "Internal Server Error",
							content = @Content(schema = @Schema(implementation = RuntimeException.class))
					)
			}
	)
	@PatchMapping("/students/{studentId}")
	public ResponseEntity<ResponseStructure<StudentResponseDTO>> updateStudentStack(@RequestParam @Valid String stack,
			@PathVariable String studentId) {
		StudentResponseDTO studentResponseDTO = userService.updateStudentStack(stack, studentId);
		return responseBuilder.success(HttpStatus.OK, "Student stack updated", studentResponseDTO);
	}





	@Operation(description = "The API endpoint is used to update the details of an existing Trainer user based on a unique Identifier."
			+ " The endpoint requires the path variable userId and the updated trainer details.",
			responses = {
					@ApiResponse(responseCode = "200", description = "Trainer updated"),
					@ApiResponse(responseCode = "400", description = "Bad Request, invalid inputs", content = @Content(schema = @Schema(anyOf = ErrorStructure.class))),
					@ApiResponse(responseCode = "404", description = "Trainer not found", content = @Content(schema = @Schema(anyOf = ErrorStructure.class))),
					@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(anyOf = RuntimeException.class)))
			})
	@PutMapping("/trainers/{trainerId}")
	public ResponseEntity<ResponseStructure<TrainerResponseDTO>> updateTrainer(
			@RequestBody @Valid TrainerRequestDTO trainerRequestDTO, @PathVariable String trainerId) {
		TrainerResponseDTO trainerResponseDTO = (TrainerResponseDTO) userService.updateUser(trainerRequestDTO,
				trainerId);
		return responseBuilder.success(HttpStatus.OK, "Trainer updated", trainerResponseDTO);
	}




	@Operation(description = "The API endpoint is used to generate a jwt token if the user credentials are valid and send it back as the response to the client."
			+ " And this token is required for all the private end points access.",
			responses = {
					@ApiResponse(responseCode = "200", description = "Jwt token"),
			})
	@PostMapping("/login")
	public ResponseEntity<ResponseStructure<UserResponse>>  login(@RequestBody @Valid LoginRequestDTO loginRequestDTO){
		return userService.login(loginRequestDTO);
	}


	@PostMapping("/login/refresh")
	public ResponseEntity<ResponseStructure<UserResponse>> refreshLogin(){
		return userService.refreshLogin();
	}

}
