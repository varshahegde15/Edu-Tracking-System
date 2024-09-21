package com.jsp.ets.rating;

import com.jsp.ets.utility.CustomResponseBuilder;
import com.jsp.ets.utility.ErrorStructure;
import com.jsp.ets.utility.ResponseStructure;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Rating Controller", description = "APIs for managing Ratings including update, and fetching.")
public class RatingController {

    private final RatingService ratingService;
    private final CustomResponseBuilder responseBuilder;


    @Operation(description = "The API endpoint is used to update the details of an existing rating based on a unique Identifier."
            + " The endpoint requires the path variable ratingId and the rating details that are to be updated.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Rating updated"),
                    @ApiResponse(responseCode = "400", description = "Bad Request, invalid inputs", content = @Content(schema = @Schema(anyOf = ErrorStructure.class))),
                    @ApiResponse(responseCode = "404", description = "Rating not found", content = @Content(schema = @Schema(anyOf = ErrorStructure.class))),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(anyOf = RuntimeException.class)))
            })
    @PutMapping("ratings/{ratingId}")
    public ResponseEntity<ResponseStructure<RatingResponseDTO>> updateRating(
            @RequestBody @Valid RatingRequestDTO ratingRequestDTO, @PathVariable String ratingId) {
        RatingResponseDTO ratingResponseDTO = ratingService.updateRating(ratingRequestDTO, ratingId);
        return responseBuilder.success(HttpStatus.OK, "Rating updated", ratingResponseDTO);
    }


    @Operation(description = "The API endpoint is used to get the details of all existing ratings based on the Student."
            + " The endpoint requires the path variable studentId using which the ratings has to be fetched.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ratings found"),
                    @ApiResponse(responseCode = "400", description = "Bad Request, invalid inputs", content = @Content(schema = @Schema(anyOf = ErrorStructure.class))),
                    @ApiResponse(responseCode = "404", description = "Student not found", content = @Content(schema = @Schema(anyOf = ErrorStructure.class))),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(anyOf = RuntimeException.class)))
            })
    @GetMapping("/students/{studentId}/ratings")
    public ResponseEntity<ResponseStructure<List<RatingResponseDTO>>> findAllRatings(
            @Valid @PathVariable String studentId) {
        List<RatingResponseDTO> response = ratingService.findAllRatings(studentId);
        return responseBuilder.success(HttpStatus.FOUND, "Ratings Found", response);
    }
}
