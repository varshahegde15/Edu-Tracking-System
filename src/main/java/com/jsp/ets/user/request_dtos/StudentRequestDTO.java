package com.jsp.ets.user.request_dtos;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Year;

@Getter
@Setter
public class StudentRequestDTO extends UserRequestDTO {

    @NotBlank(message = "Degree is required.")
    @Size(max = 100, message = "Degree name must not exceed 100 characters.")
    private String degree;

    @NotBlank(message = "Stream is required.")
    @Size(max = 100, message = "Stream name must not exceed 100 characters.")
    private String stream;

    @NotNull(message = "Year of passing is required.")
    @PastOrPresent(message = "Year of passing must be in the past or the current year.")
    private Year yop;

    @NotNull(message = "Degree percentage is required.")
    @DecimalMin(value = "0.0", inclusive = false, message = "Degree percentage must be greater than 0.")
    @DecimalMax(value = "100.0", inclusive = true, message = "Degree percentage must be less than or equal to 100.")
    private Double degreePercentage;

    @NotNull(message = "Tenth percentage is required.")
    @DecimalMin(value = "0.0", inclusive = false, message = "Tenth percentage must be greater than 0.")
    @DecimalMax(value = "100.0", inclusive = true, message = "Tenth percentage must be less than or equal to 100.")
    private Double tenthPercentage;

    @NotNull(message = "Twelfth percentage is required.")
    @DecimalMin(value = "0.0", inclusive = false, message = "Twelfth percentage must be greater than 0.")
    @DecimalMax(value = "100.0", inclusive = true, message = "Twelfth percentage must be less than or equal to 100.")
    private Double twelvethPercentage;
}
