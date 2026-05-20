package org.example.corepayauthservice.application.command;

import lombok.Builder;

@Builder
public record LoginCommand(
        String email,
        String password
) {
}
