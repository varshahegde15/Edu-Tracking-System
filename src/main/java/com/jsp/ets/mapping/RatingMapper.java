package com.jsp.ets.mapping;

import com.jsp.ets.rating.Rating;
import com.jsp.ets.rating.RatingRequestDTO;
import com.jsp.ets.rating.RatingResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class RatingMapper {

    public Rating mapToRatingEntity(RatingRequestDTO ratingRequestDTO, Rating rating) {
        rating.setFeedback(ratingRequestDTO.getFeedback());
        rating.setRatings(ratingRequestDTO.getRatings());

        return rating;
    }

    public RatingResponseDTO mapToRatingResponse(Rating rating) {

        RatingResponseDTO ratingResponseDTO = new RatingResponseDTO();

        ratingResponseDTO.setFeedback(rating.getFeedback());
        ratingResponseDTO.setRatings(rating.getRatings());
        ratingResponseDTO.setRatingId(rating.getRatingId());
        ratingResponseDTO.setSubject(rating.getSubject());

        return ratingResponseDTO;
    }
}
