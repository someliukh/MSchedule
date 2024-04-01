package com.example.mschedule;

import com.example.mschedule.service.impl.AuthServiceImpl;
import com.example.mschedule.dto.RegisterRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.example.mschedule.enums.Role.ADMIN;

@SpringBootApplication
public class MScheduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(MScheduleApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            AuthServiceImpl service
    ) {
        return args -> {
            var admin = RegisterRequest.builder()
                    .firstname("Admin")
                    .lastname("Admin")
                    .email("admin@mail.com")
                    .password("password")
                    .role(ADMIN)
                    .build();
            System.out.println("Admin token: " + service.register(admin).getAccessToken());
        };
    }

}
