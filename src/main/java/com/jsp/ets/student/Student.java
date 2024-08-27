package com.jsp.ets.student;

import java.time.Year;

import com.jsp.ets.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "students")
@Getter
@Setter
public class Student extends User {

	@Column(name = "degree")
	private String degree;
	
	@Column(name = "stream")
	private String stream;
	
	@Column(name = "yop")
	private Year   yop;
	
	@Column(name = "degree_percentage")
	private double degreePercentage;
	
	@Column(name = "tenth_percentage")
	private double tenthPercentage;
	
	@Column(name = "twelveth_percentage")
	private double twelvethPercentage;
}
