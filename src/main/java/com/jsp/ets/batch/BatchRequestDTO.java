package com.jsp.ets.batch;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.jsp.ets.user.Subject;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BatchRequestDTO {

    @NotNull(message = "Title cannot be null")
    @NotEmpty(message = "Title cannot be empty")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    private String title;

    @NotNull(message = "Subjects cannot be null")
    @Size(min = 1, message = "There must be at least one subject")
    private List<Subject> subjects;

    @NotNull(message = "startingDate cannot be null")
    private LocalDateTime startingDate;

    @NotNull(message = "beginsAt cannot be null")
    private LocalTime beginsAt;

    @NotNull(message = "endsAt cannot be null")
    private LocalTime endsAt;
}
