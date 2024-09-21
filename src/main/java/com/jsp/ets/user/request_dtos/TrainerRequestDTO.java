package com.jsp.ets.user.request_dtos;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TrainerRequestDTO extends UserRequestDTO {

    @NotNull(message = "Subjects list cannot be null.")
    @NotEmpty(message = "Subjects list cannot be empty.")
    @Enumerated(EnumType.STRING)
    private List<com.jsp.ets.user.Subject> subjects;
}
