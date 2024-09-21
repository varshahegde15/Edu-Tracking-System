package com.jsp.ets.user;

import com.jsp.ets.batch.Batch;
import com.jsp.ets.rating.Rating;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Year;
import java.util.List;

@Entity
@Table(name = "students")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Student extends User {

    @Column(name = "degree")
    private String degree;

    @Column(name = "stream")
    private String stream;

    @Column(name = "yop")
    private Year yop;

    @Column(name = "degree_percentage")
    private double degreePercentage;

    @Column(name = "tenth_percentage")
    private double tenthPercentage;

    @Column(name = "twelveth_percentage")
    private double twelvethPercentage;

    @Column(name = "tech_stack")
    @Enumerated(EnumType.STRING)
    private Stack stack;

    @Column(name = "ratings")
    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
    private List<Rating> ratings;

    @ManyToMany(mappedBy = "students")
    private List<Batch> batchs;
}
