package com.example.mschedule.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static com.example.mschedule.enums.Permission.*;
import static com.example.mschedule.enums.Role.ADMIN;
import static com.example.mschedule.enums.Role.MEMBER;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private static final String[] WHITE_LIST_URL = {"/api/v1/auth/**"};
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers(WHITE_LIST_URL)
                                .permitAll()
                                .requestMatchers("/api/v1/admin/**").hasAnyRole(ADMIN.name(), MEMBER.name())
                                .requestMatchers(GET, "/api/v1/admin/**").hasAnyRole(ADMIN.name())
                                .requestMatchers(POST, "/api/v1/admin/**").hasAnyRole(ADMIN.name())
                                .requestMatchers(PUT, "/api/v1/admin/**").hasAnyRole(ADMIN.name())
                                .requestMatchers(DELETE, "/api/v1/admin/**").hasAnyRole(ADMIN.name())

                                .requestMatchers(GET, "/api/v1/staff").hasAnyRole(ADMIN.name())
                                .requestMatchers(POST, "/api/v1/staff/{id}/create-task").hasAnyRole(ADMIN.name())
                                .requestMatchers(GET, "/api/v1/staff/{id}").hasAnyRole(ADMIN.name(), MEMBER.name())
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->
                        logout.logoutUrl("/api/v1/auth/logout")
                                .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                )
        ;

        return http.build();
    }
}
