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

	@PutMapping("ratings/{ratingId}")
	public ResponseEntity<ResponseStructure<RatingResponseDTO>> updateRating(
			@RequestBody @Valid RatingRequestDTO ratingRequestDTO, @PathVariable String ratingId) {
		RatingResponseDTO ratingResponseDTO = ratingService.updateRating(ratingRequestDTO, ratingId);
		return responseBuilder.success(HttpStatus.OK, "Rating updated", ratingResponseDTO);
	}

	@GetMapping("/students/{studentId}/ratings")
	public ResponseEntity<ResponseStructure<List<RatingResponseDTO>>> findAllRatings(
			@Valid @PathVariable String studentId) {
		List<RatingResponseDTO> response = ratingService.findAllRatings(studentId);
		return responseBuilder.success(HttpStatus.FOUND, "Ratings Found", response);
	}
}
