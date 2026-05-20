package org.example.corepayauthservice.infrastructure.kafka.event;

import lombok.Builder;

@Builder
public record UserUpdatePasswordEvent(
        String email,
        String password
) {
}
