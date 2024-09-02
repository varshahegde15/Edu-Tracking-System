package com.jsp.ets.mapping;

import org.springframework.stereotype.Component;

import com.jsp.ets.rating.Rating;
import com.jsp.ets.rating.RatingRequestDTO;
import com.jsp.ets.rating.RatingResponseDTO;

@Component
public class RatingMapper {

	public Rating mapToRatingEntity(RatingRequestDTO ratingRequestDTO, Rating rating) {
		rating.setFeedback(ratingRequestDTO.getFeedback());
		rating.setRating(ratingRequestDTO.getRating());

		return rating;
	}

	public RatingResponseDTO mapToRatingResponse(Rating rating) {

		RatingResponseDTO ratingResponseDTO = new RatingResponseDTO();

		ratingResponseDTO.setFeedback(rating.getFeedback());
		ratingResponseDTO.setRating(rating.getRating());
		ratingResponseDTO.setRatingId(rating.getRatingId());
		ratingResponseDTO.setSubject(rating.getSubject());

		return ratingResponseDTO;
	}
}
