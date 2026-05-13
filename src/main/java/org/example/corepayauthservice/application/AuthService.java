package org.example.corepayauthservice.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.corepayauthservice.domain.AuthUser;
import org.example.corepayauthservice.domain.AuthUserRepository;
import org.example.corepayauthservice.security.JwtProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthUserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // SecurityConfig에서 등록한 BCrypt 빈
    private final JwtProvider jwtProvider;

    //로그인 로직: 이메일과 비밀번호를 검증하고 JWT를 반환합니다.
    @Transactional(readOnly = true)
    public String login(String email, String password) {
        // 1. 이메일로 유저 찾기
        AuthUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 이메일입니다."));

        // 2. 비밀번호 일치 여부 확인 (평문 비밀번호와 DB의 해시된 비밀번호 비교)
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 3. 검증 성공 시 JWT 토큰 발급 (유저 ID, 이메일, 권한 전달)
        String token = jwtProvider.generateToken(
                user.getId(),
                user.getEmail(),
                user.getRole().name()
        );

        log.info("로그인 성공 및 토큰 발급 완료 - UserID: {}", user.getId());
        return token;
    }

    //(테스트용) 임시 회원가입 로직: DB에 회원이 있어야 로그인을 테스트할 수 있으므로 추가합니다.
    @Transactional
    public Long signup(String email, String rawPassword) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }

        // 비밀번호 해시 암호화
        String encodedPassword = passwordEncoder.encode(rawPassword);

        AuthUser newUser = AuthUser.builder()
                .email(email)
                .password(encodedPassword)
                // role은 builder에서 기본값(ROLE_USER)으로 설정됨
                .build();

        return userRepository.save(newUser).getId();
    }
}
