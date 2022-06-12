package com.jisu.jpashopSecurity.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @NotEmpty
    private String username;

    private String password;

    private String email;

    private String role;

    private String provider;

    private String providerId;

    @CreationTimestamp
    private Timestamp createDate;

    public Member(String username, String password, String email, String role, String provider, String providerId, Timestamp createDate) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
        this.createDate = createDate;
    }

    public Member(String username, String password, String email) {
        this.username=username;
        this.password=password;
        this.email=email;
    }

    public void hasRole(String role) {
        this.role=role;
    }
    public void encodePassword(String password) {
        this.password = password;

    }
}
