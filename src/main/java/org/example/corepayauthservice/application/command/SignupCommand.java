package org.example.corepayauthservice.application.command;

import lombok.Builder;
import org.example.corepayauthservice.domain.Role;

@Builder
public record SignupCommand(
        Long id,
        String email,
        String password,
        Role role
) {
}
