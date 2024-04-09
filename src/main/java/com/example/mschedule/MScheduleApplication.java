package com.example.mschedule;

import com.example.mschedule.dto.auth.RegisterRequest;
import com.example.mschedule.service.impl.AuthServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.example.mschedule.enums.Role.ADMIN;
import static com.example.mschedule.enums.Role.MEMBER;

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
                    .email("kr2235yana@gmail.com")
                    .password("Password")
                    .role(ADMIN)
                    .build();
            System.out.println("Admin token: " + service.register(admin).getAccessToken());

            var member = RegisterRequest.builder()
                    .firstname("Stepan")
                    .lastname("Omeliukh")
                    .email("ctepan2001ome@gmail.com")
                    .password("Password")
                    .role(MEMBER)
                    .build();
            System.out.println("Member token: " + service.register(member).getAccessToken());
        };
    }

}
