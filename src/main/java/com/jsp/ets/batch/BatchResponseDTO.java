package com.jsp.ets.batch;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.jsp.ets.user.Student;
import com.jsp.ets.user.Subject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BatchResponseDTO {

	private String batchId;
	private String title;
	private List<Subject> subjects;
	private LocalDateTime startingDate;
	private List<Student> students;
	private LocalTime beginsAt;
	private LocalTime endsAt;
}
