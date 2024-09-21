package com.jsp.ets.user.response_dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.Year;

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
