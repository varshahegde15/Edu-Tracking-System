package com.jsp.ets.user.response_dtos;

import com.jsp.ets.user.Subject;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TrainerResponseDTO extends UserResponse {

    private List<Subject> subjects;
}
