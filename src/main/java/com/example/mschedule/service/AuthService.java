package com.example.mschedule.service;

import com.example.mschedule.dto.auth.AuthRequest;
import com.example.mschedule.dto.auth.AuthResponse;
import com.example.mschedule.dto.auth.RegisterRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.mail.MessagingException;
import java.io.IOException;

public interface AuthService {

    AuthResponse register(RegisterRequest request) throws MessagingException;

    AuthResponse authenticate(AuthRequest request);

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

}
