package com.gsm._8th.class4.backend.task12.domain.auth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String phonenumber;

    @Column(nullable = false, unique = true)
    private String address;

    @Column(nullable = false)
    private boolean isadult;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberRole role;

    public User(String username, String password, MemberRole role){
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
