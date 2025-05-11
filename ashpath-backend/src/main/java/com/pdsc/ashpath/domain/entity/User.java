package com.pdsc.ashpath.domain.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import com.pdsc.ashpath.domain.enums.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "APP_USER")
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "APP_USER_ID")
    private Long id;

    @Column(name = "APP_USER_EMAIL", length = 50, nullable = false)
    private String email;

    @Column(name = "APP_USER_PASSWORD", length = 50, nullable = false)
    private String password;

    @Column(name = "APP_USER_FULL_NAME", length = 100, nullable = false)
    private String fullName;

    @Column(name = "APP_USER_REGISTRATION_DATE", columnDefinition = "TIMESTAMP")
    private LocalDateTime registrationDate;

    @Column(name = "APP_USER_LAST_ACTIVE_DATE", columnDefinition = "TIMESTAMP")
    private LocalDateTime lastActivityDate;

    @Enumerated
    @Column(name = "APP_USER_ROLE", nullable = false)
    private UserRole userRole;

    public User(
        String email,
        String password,
        String fullName,
        LocalDateTime registrationDate,
        LocalDateTime lastActivityDate,
        UserRole userRole
    ) {
        setEmail(email);
        setPassword(password);
        setFullName(fullName);
        setRegistrationDate(registrationDate);
        setLastActivityDate(lastActivityDate);
        setUserRole(userRole);
    }

    @Override
    public int hashCode() {

        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }

        final User other =(User) obj;
        return Objects.equals(this.id, other.id);
    }
}
