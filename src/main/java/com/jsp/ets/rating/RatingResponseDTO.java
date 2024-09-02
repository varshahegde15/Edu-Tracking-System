package com.jsp.ets.rating;

import com.jsp.ets.user.Subject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingResponseDTO {

	private String ratingId;
	private Subject subject;
	private int rating;
	private String feedback;

}
