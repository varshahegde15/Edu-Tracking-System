package com.jsp.ets.rating;

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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ratings")
@Inheritance(strategy = InheritanceType.JOINED)
@EntityListeners(AuditingEntityListener.class)
public class Rating {

	@Column(name = "rating_Id")
	@GenerateSequenceId
	@Id
	private String ratingId;

	@Column(name = "subject")
	@Enumerated(EnumType.STRING)
	private Subject subject;

	@Column(name = "ratings")
	private int ratings;

	@Column(name = "feedback")
	private String feedback;

	@ManyToOne
	private Student student;

}
