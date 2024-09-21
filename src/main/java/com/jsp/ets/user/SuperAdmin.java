package com.jsp.ets.user;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "super_admin")
@EntityListeners(AuditingEntityListener.class)
public class SuperAdmin extends User {

}
