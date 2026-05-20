package org.example.corepayauthservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication(scanBasePackages = {
        "org.example.corepayauthservice",
        "org.example.corepaycommon"
})
public class CorepayAuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CorepayAuthServiceApplication.class, args);
    }

}
