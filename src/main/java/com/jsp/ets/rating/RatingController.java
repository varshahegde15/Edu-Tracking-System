package com.jsp.ets.rating;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.ets.utility.CustomResponseBuilder;
import com.jsp.ets.utility.ResponseStructure;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class RatingController {

	private final RatingService ratingService;
	private final CustomResponseBuilder responseBuilder;

	@PutMapping("ratings/{rating_id}")
	public ResponseEntity<ResponseStructure<RatingResponseDTO>> updateRating(
			@RequestBody @Valid RatingRequestDTO ratingRequestDTO, @PathVariable String rating_id) {
		RatingResponseDTO ratingResponseDTO = ratingService.updateRating(ratingRequestDTO, rating_id);
		return responseBuilder.success(HttpStatus.OK, "Rating updated", ratingResponseDTO);
	}

	@GetMapping("/students/{student_id}/ratings")
	public ResponseEntity<ResponseStructure<List<RatingResponseDTO>>> findAllRatings(
			@Valid @PathVariable String student_id) {
		List<RatingResponseDTO> response = ratingService.findAllRatings(student_id);
		return responseBuilder.success(HttpStatus.FOUND, "Ratings Found", response);
	}
}
