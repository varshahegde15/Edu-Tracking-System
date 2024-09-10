package com.jsp.ets.batch;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.jsp.ets.config.GenerateSequenceId;
import com.jsp.ets.user.Student;
import com.jsp.ets.user.Subject;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "batches")
@Inheritance(strategy = InheritanceType.JOINED)
@EntityListeners(AuditingEntityListener.class)
public class Batch {

	@Id
	@GenerateSequenceId
	@Column(name = "batch_id")
	private String batchId;

	@Column(name = "title")
	private String title;

	private List<Subject> subjects;

	@Column(name = "starting_date")
	private LocalDateTime startingDate;

	@Column(name = "closed_date")
	private LocalDateTime closedDate;

	@Column(name = "begins_at")
	private LocalTime beginsAt;

	@Column(name = "ends_at")
	private LocalTime endsAt;

	@Column(name = "batch_status")
	@Enumerated(EnumType.STRING)
	private BatchStatus batchStatus;

	@ManyToMany
	private List<Student> students;

}
