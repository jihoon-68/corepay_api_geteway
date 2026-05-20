package org.example.corepayauthservice.infrastructure.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.corepayauthservice.application.AuthService;
import org.example.corepayauthservice.application.command.SignupCommand;
import org.example.corepayauthservice.application.command.UpdatePasswordCommand;
import org.example.corepayauthservice.infrastructure.kafka.event.UserCreatedEvent;
import org.example.corepayauthservice.infrastructure.kafka.event.UserUpdatePasswordEvent;
import org.example.corepaycommon.log.KafkaMdcHelper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthResultConsumer {

    private final AuthService authService;
    private final KafkaMdcHelper kafkaMdcHelper;

    @KafkaListener(topics = "user-created-topic", groupId = "auth-group")
    public void consumerUserCreated(@Payload String message, @Header(value = "X-Trace-Id", required = false) String traceId){
        kafkaMdcHelper.processEventWithMdc(traceId, message, UserCreatedEvent.class, event ->{
            SignupCommand command = SignupCommand.builder()
                    .id(event.id())
                    .email(event.email())
                    .password(event.password())
                    .role(event.role())
                    .build();
            authService.signup(command);
        });
    }

    @KafkaListener(topics = "user-update-password-topic", groupId = "auth-group")
    public void consumerUserUpdatePassword(@Payload String message, @Header(value = "X-Trace-Id", required = false) String traceId){
        kafkaMdcHelper.processEventWithMdc(traceId, message, UserUpdatePasswordEvent.class, event ->{
            UpdatePasswordCommand command = UpdatePasswordCommand.builder()
                    .email(event.email())
                    .password(event.password())
                    .build();
            authService.updatePassword(command);
        });
    }
}
