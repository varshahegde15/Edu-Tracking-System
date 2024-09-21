package com.jsp.ets.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@Table(name = "trainers")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Trainer extends User {

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<Subject> subjects;

}
