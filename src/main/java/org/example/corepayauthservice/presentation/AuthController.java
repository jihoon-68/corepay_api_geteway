package org.example.corepayauthservice.presentation;

import lombok.RequiredArgsConstructor;
import org.example.corepayauthservice.application.AuthService;
import org.example.corepayauthservice.application.dto.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    //로그인 API: POST /api/auth/login

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest request) {
        // 서비스에서 로그인 검증 및 토큰 발급
        String token = authService.login(request.email(), request.password());

        // 클라이언트에게 "Bearer {토큰}" 형식으로 반환 (또는 Authorization 헤더에 담아도 됩니다)
        return ResponseEntity.ok(Map.of("accessToken", "Bearer " + token));
    }

    //(테스트용) 회원가입 API: POST /api/auth/signup
    @PostMapping("/signup")
    public ResponseEntity<Map<String, Long>> signup(@RequestBody LoginRequest request) {
        Long userId = authService.signup(request.email(), request.password());
        return ResponseEntity.ok(Map.of("createdUserId", userId));
    }
}