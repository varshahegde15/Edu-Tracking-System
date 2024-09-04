package com.jsp.ets.rating;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jsp.ets.exception.RatingNotFoundByIdException;
import com.jsp.ets.exception.UserNotFoundByIdException;
import com.jsp.ets.mapping.RatingMapper;
import com.jsp.ets.user.Student;
import com.jsp.ets.user.User;
import com.jsp.ets.user.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepo;
    private final RatingMapper ratingMapper;
    private final UserRepository userRepo;

    public RatingResponseDTO updateRating(RatingRequestDTO ratingRequestDTO, String rating_id) {
        return ratingRepo.findById(rating_id)
                .map(existingRating -> ratingMapper.mapToRatingEntity(ratingRequestDTO, existingRating))
                .map(ratingRepo::save)
                .map(ratingMapper::mapToRatingResponse)
                .orElseThrow(() -> new RatingNotFoundByIdException("Rating not found by id"));
    }

    public List<RatingResponseDTO> findAllRatings(String studentId) {
        return userRepo.findById(studentId)
                .filter(Student.class::isInstance)
                .map(Student.class::cast)
                .map(Student::getRatings)
                .map(ratings -> ratings.stream()
                        .map(ratingMapper::mapToRatingResponse)
                        .collect(Collectors.toList()))
                .orElseThrow(() -> new UserNotFoundByIdException("Student not found by id"));
    }
}
