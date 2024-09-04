package com.jsp.ets.user.response_dtos;

import java.time.Year;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponseDTO extends UserResponse {

	private String degree;
	private String stream;
	private Year yop;
	private double degreePercentage;
	private double tenthPercentage;
	private double twelvethPercentage;

}
