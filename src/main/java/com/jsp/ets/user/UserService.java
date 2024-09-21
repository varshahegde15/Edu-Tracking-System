package com.jsp.ets.user;

import com.jsp.ets.exception.InvalidOtpException;
import com.jsp.ets.exception.InvalidStackException;
import com.jsp.ets.exception.RegistrationSessionExpiredException;
import com.jsp.ets.exception.UserNotFoundByIdException;
import com.jsp.ets.mapping.UserMapper;
import com.jsp.ets.rating.Rating;
import com.jsp.ets.rating.RatingRepository;
import com.jsp.ets.security.JwtService;
import com.jsp.ets.user.request_dtos.*;
import com.jsp.ets.user.response_dtos.StudentResponseDTO;
import com.jsp.ets.user.response_dtos.UserResponse;
import com.jsp.ets.utility.CacheHelper;
import com.jsp.ets.utility.MailSenderService;
import com.jsp.ets.utility.MessageModel;
import com.jsp.ets.utility.ResponseStructure;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepo;
    private final RatingRepository ratingRepo;
    private final MailSenderService mailSender;
    private final Random random;
    private final CacheHelper cacheHelper;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserService(UserMapper userMapper, UserRepository userRepo, RatingRepository ratingRepo, MailSenderService mailSender, Random random, CacheHelper cacheHelper, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userMapper = userMapper;
        this.userRepo = userRepo;
        this.ratingRepo = ratingRepo;
        this.mailSender = mailSender;
        this.random = random;
        this.cacheHelper = cacheHelper;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Value("${my_app.jwt.access_expiry}")
    private long accessExpiry;

    @Value("${my_app.jwt.refresh_expiry}")
    private long refreshExpiry;


    public UserResponse registerUser(RegistrationRequestDTO registrationRequestDTO, UserRole role) throws MessagingException {
        User user = switch (role) {
            case ADMIN -> new Admin();
            case HR -> new HR();
            case TRAINER -> new Trainer();
            case STUDENT -> new Student();
            default -> throw new IllegalArgumentException("Unexpected value: " + role);
        };

        user = userMapper.mapToUserEntity(registrationRequestDTO, user);
        user.setRole(role);
        Integer otp = random.nextInt(100000, 999999);

        user = cacheHelper.userCache(user);
        otp = cacheHelper.otpCache(otp, user.getEmail());

        sendOtpToMailId(user.getEmail(), otp);
        return userMapper.mapToUserResponse(user);
    }

    public UserResponse updateUser(UserRequestDTO userRequestDTO, String userId) {
        return userRepo.findById(userId)
                .map(user -> {
                    switch (user.getRole()) {
                        case STUDENT: {
                            Student student = (Student) user;
                            StudentRequestDTO studentRequestDTO = (StudentRequestDTO) userRequestDTO;
                            student = userMapper.mapToStudentEntity(studentRequestDTO, student);
                            student = userRepo.save(student);
                            return userMapper.mapToStudentResponse(student);
                        }
                        case TRAINER: {
                            Trainer trainer = (Trainer) user;
                            TrainerRequestDTO trainerRequestDTO = (TrainerRequestDTO) userRequestDTO;
                            trainer = userMapper.mapToTrainerEntity(trainerRequestDTO, trainer);
                            trainer = userRepo.save(trainer);
                            return userMapper.mapToTrainerResponse(trainer);
                        }
                        default:
                            throw new IllegalArgumentException("Unexpected value: " + user.getRole());
                    }
                })
                .orElseThrow(() -> new UserNotFoundByIdException("User not found"));
    }

    public StudentResponseDTO updateStudentStack(String stack, String studentId) {
        return userRepo.findById(studentId)
                .filter(Student.class::isInstance)
                .map(Student.class::cast)
                .map(student -> {
                    List<Subject> subjects = Optional.ofNullable(Stack.valueOf(stack))
                            .map(Stack::getSubjects)
                            .orElseThrow(() -> new InvalidStackException("Invalid stack"));
                    subjects.forEach(subject -> {
                        Rating rating = new Rating();
                        rating.setStudent(student);
                        rating.setSubject(subject);
                        ratingRepo.save(rating);
                    });
                    student.setStack(Stack.valueOf(stack));
                    return userRepo.save(student);
                })
                .map(userMapper::mapToStudentResponse)
                .orElseThrow(() -> new UserNotFoundByIdException("Student not found by id"));
    }

    private void sendOtpToMailId(String email, int otp) throws MessagingException {
        String text = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>OTP Notification</title>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            background-color: #f9f9f9;\n" +
                "            display: flex;\n" +
                "            justify-content: center;\n" +
                "            align-items: center;\n" +
                "            height: 100vh;\n" +
                "            margin: 0;\n" +
                "        }\n" +
                "\n" +
                "        .container {\n" +
                "            background-color: white;\n" +
                "            padding: 20px;\n" +
                "            border-radius: 10px;\n" +
                "            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);\n" +
                "            max-width: 400px;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        h2 {\n" +
                "            font-size: 24px;\n" +
                "            color: #333;\n" +
                "        }\n" +
                "\n" +
                "        p {\n" +
                "            font-size: 16px;\n" +
                "            color: #555;\n" +
                "            margin-bottom: 20px;\n" +
                "        }\n" +
                "\n" +
                "        .otp {\n" +
                "            font-weight: bold;\n" +
                "            font-size: 18px;\n" +
                "            color: #d9534f;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "    <div class=\"container\">\n" +
                "        <h2>OTP Verification</h2>\n" +
                "        <p>Dear User,</p>\n" +
                "        <p>The OTP to verify your EDU-Tracking-System account is <span class=\"otp\">" + otp + "</span>. It will be valid for the next 5 minutes only.</p>\n" +
                "        <p>Please enter this OTP to complete your verification.</p>\n" +
                "    </div>\n" +
                "\n" +
                "</body>\n" +
                "</html>\n";

        MessageModel messageModel = new MessageModel();
        messageModel.setTo(email);
        messageModel.setSubject("Verify your email for EDU-Tracking");
        messageModel.setSendDate(new Date());
        messageModel.setText(text);

        mailSender.sendMail(messageModel);
    }

    public UserResponse verifyUser(@Valid OtpRequestDto otpRequestDto) {
        Integer cachedOtp = cacheHelper.getCachedOtp(otpRequestDto.getEmail());

        if (cachedOtp == 0 || !cachedOtp.equals(otpRequestDto.getOtp())) {
            throw new InvalidOtpException("Invalid Otp entered");
        }

        User cachedUser = cacheHelper.getRegisteringUser(otpRequestDto.getEmail());
        if (!cachedUser.getEmail().equals(otpRequestDto.getEmail()))
            throw new RegistrationSessionExpiredException("Registration session is expired");

        User user = userRepo.save(cachedUser);
        return userMapper.mapToUserResponse(user);
    }


    public ResponseEntity<ResponseStructure<UserResponse>> login(@Valid LoginRequestDTO loginRequestDTO) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        if (authentication.isAuthenticated()) {
            return handleAuthenticatedUser(loginRequestDTO.getEmail(), true);
        } else {
            throw new BadCredentialsException("Invalid email or password");
        }
    }

    public ResponseEntity<ResponseStructure<UserResponse>> refreshLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        if (email != null) {
            return handleAuthenticatedUser(email, false);
        } else {
            throw new UserNotFoundByIdException("User is not authenticated");
        }
    }


    private ResponseEntity<ResponseStructure<UserResponse>> handleAuthenticatedUser(String email, boolean includeRefreshToken) {
        return userRepo.findByEmail(email)
                .map(user -> {
                    HttpHeaders httpHeaders = grantAccess(user, includeRefreshToken);
                    return ResponseEntity.ok()
                            .headers(httpHeaders)
                            .body(ResponseStructure.create(HttpStatus.OK.value(), "Cookies created", userMapper.mapToUserResponse(user)));
                })
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }

    private HttpHeaders grantAccess(User user, boolean includeRefreshToken) {
        String accessToken = jwtService.generateAccessToken(user.getUserId(), user.getEmail(), user.getRole().name());
        HttpHeaders headers = new HttpHeaders();

        headers.add(HttpHeaders.SET_COOKIE, generateCookie("at", accessToken, accessExpiry * 60));

        if (includeRefreshToken) {
            String refreshToken = jwtService.generateRefreshToken(user.getUserId(), user.getEmail(), user.getRole().name());
            headers.add(HttpHeaders.SET_COOKIE, generateCookie("rt", refreshToken, refreshExpiry * 60));
        }

        return headers;
    }


    private String generateCookie(String name, String value, long maxAge) {
        return ResponseCookie.from(name, value)
                .domain("localhost")
                .path("/")
                .secure(false)
                .httpOnly(true)
                .sameSite("Lax")
                .maxAge(maxAge)
                .build()
                .toString();
    }



}
