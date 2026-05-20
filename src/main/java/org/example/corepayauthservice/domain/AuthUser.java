package org.example.corepayauthservice.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Table(name = "auth_users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthUser {

    @Id
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role role;

    @Builder
    public AuthUser(Long id ,String email, String password, Role role) {
        this.id = id;
        this.email = email;
        this.password = password;
        // 권한이 안 들어오면 기본값으로 일반 유저(USER) 설정
        this.role = role != null ? role : Role.USER;
    }

    public void updatePassword(String password){
        if(password != null && !Objects.equals(this.password, password)){
            this.password =password;
        }
    }

}