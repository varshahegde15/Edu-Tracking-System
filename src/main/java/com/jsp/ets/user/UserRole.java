package com.jsp.ets.user;

import java.util.List;

public enum UserRole {

    SUPER_ADMIN(List.of(Privilege.WRITE_ADMIN, Privilege.WRITE_HR, Privilege.READ_USER, Privilege.READ_BATCH,
            Privilege.READ_MOCK, Privilege.READ_RATING, Privilege.READ_REQUIREMENT, Privilege.READ_SCHEDULE,
            Privilege.READ_TECH_REPORT)),
    ADMIN(List.of(Privilege.WRITE_ADMIN, Privilege.WRITE_TRAINER, Privilege.WRITE_BATCH, Privilege.READ_USER,
            Privilege.READ_BATCH, Privilege.READ_MOCK, Privilege.READ_RATING, Privilege.READ_REQUIREMENT,
            Privilege.READ_SCHEDULE, Privilege.READ_TECH_REPORT)),
    HR(List.of(Privilege.WRITE_REQUIREMENT, Privilege.WRITE_SCHEDULE, Privilege.WRITE_HR, Privilege.READ_USER,
            Privilege.READ_BATCH, Privilege.READ_MOCK, Privilege.READ_RATING, Privilege.READ_REQUIREMENT,
            Privilege.READ_SCHEDULE, Privilege.READ_TECH_REPORT)),
    TRAINER(List.of(Privilege.WRITE_MOCK, Privilege.WRITE_TRAINER, Privilege.WRITE_RATING, Privilege.WRITE_TECH_REPORT,
            Privilege.READ_USER, Privilege.READ_BATCH, Privilege.READ_MOCK, Privilege.READ_RATING,
            Privilege.READ_REQUIREMENT, Privilege.READ_SCHEDULE, Privilege.READ_TECH_REPORT)),
    STUDENT(List.of(Privilege.WRITE_STUDENT, Privilege.READ_USER, Privilege.READ_BATCH, Privilege.READ_MOCK,
            Privilege.READ_RATING, Privilege.READ_REQUIREMENT, Privilege.READ_SCHEDULE, Privilege.READ_TECH_REPORT));

    private List<Privilege> privileges;

    private UserRole(List<Privilege> privileges) {
        this.privileges = privileges;
    }

    public List<Privilege> getPrivileges() {
        return privileges;
    }
}
