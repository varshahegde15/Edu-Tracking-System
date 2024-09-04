package com.jsp.ets.user.response_dtos;

import java.util.List;

import com.jsp.ets.user.Subject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainerResponseDTO extends UserResponse {

	private List<Subject> subjects;
}
