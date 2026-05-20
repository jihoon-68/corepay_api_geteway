package org.example.corepayauthservice.application;

import org.example.corepayauthservice.application.command.LoginCommand;
import org.example.corepayauthservice.application.command.SignupCommand;
import org.example.corepayauthservice.application.command.UpdatePasswordCommand;

public interface AuthInterface {
    String login(LoginCommand command);
    void signup(SignupCommand command);
    void updatePassword(UpdatePasswordCommand command);
}
