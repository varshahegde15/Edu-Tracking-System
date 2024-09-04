package com.jsp.ets.rating;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingRequestDTO {

	@Min(value = 1, message = "Rating must be at least 1")
	@Max(value = 5, message = "Rating must be at most 5")
	private int ratings;

	@NotBlank(message = "Feedback must not be blank")
	private String feedback;
}
