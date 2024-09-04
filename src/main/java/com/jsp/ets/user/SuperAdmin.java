package com.jsp.ets.user;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;

@Entity
@Table(name = "super_admin")
@EntityListeners(AuditingEntityListener.class)
public class SuperAdmin extends User {

}
