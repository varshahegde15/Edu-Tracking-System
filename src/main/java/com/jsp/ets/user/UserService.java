package com.jsp.ets.user;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jsp.ets.exception.InvalidStackException;
import com.jsp.ets.exception.UserNotFoundByIdException;
import com.jsp.ets.mapping.UserMapper;
import com.jsp.ets.rating.Rating;
import com.jsp.ets.rating.RatingRepository;
import com.jsp.ets.user.request_dtos.RegistrationRequestDTO;
import com.jsp.ets.user.request_dtos.StudentRequestDTO;
import com.jsp.ets.user.request_dtos.TrainerRequestDTO;
import com.jsp.ets.user.request_dtos.UserRequestDTO;
import com.jsp.ets.user.response_dtos.StudentResponseDTO;
import com.jsp.ets.user.response_dtos.UserResponse;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepo;
    private final RatingRepository ratingRepo;

    public UserResponse saveUser(RegistrationRequestDTO registrationRequestDTO, UserRole role) {
        User user = switch (role) {
            case ADMIN -> new Admin();
            case HR -> new HR();
            case TRAINER -> new Trainer();
            case STUDENT -> new Student();
            default -> throw new IllegalArgumentException("Unexpected value: " + role);
        };

        user = userMapper.mapToUserEntity(registrationRequestDTO, user);
        user.setRole(role);
        user = userRepo.save(user);

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
}
