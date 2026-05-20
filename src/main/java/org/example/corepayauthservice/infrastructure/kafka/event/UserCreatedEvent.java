package org.example.corepayauthservice.infrastructure.kafka.event;

import lombok.Builder;
import org.example.corepayauthservice.domain.Role;

@Builder
public record UserCreatedEvent(
        Long id,
        String email,
        String password,
        Role role
) {
}
