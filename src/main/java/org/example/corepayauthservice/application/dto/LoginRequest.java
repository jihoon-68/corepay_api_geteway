package org.example.corepayauthservice.application.dto;

import lombok.Builder;

@Builder
public record LoginRequest(
        String email,
        String password
) {}