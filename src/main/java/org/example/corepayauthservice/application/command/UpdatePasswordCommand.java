package org.example.corepayauthservice.application.command;

import lombok.Builder;

@Builder
public record UpdatePasswordCommand(
        String email,
        String password
) {
}
