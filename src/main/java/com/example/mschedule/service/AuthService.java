package com.example.mschedule.service;

import com.example.mschedule.dto.auth.AuthRequest;
import com.example.mschedule.dto.auth.AuthResponse;
import com.example.mschedule.dto.auth.RegisterRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse authenticate(AuthRequest request);

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

}
