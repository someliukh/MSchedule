package com.example.mschedule.controller;

import com.example.mschedule.dto.auth.AuthRequest;
import com.example.mschedule.dto.auth.AuthResponse;
import com.example.mschedule.dto.auth.RegisterRequest;
import com.example.mschedule.entity.User;
import com.example.mschedule.service.impl.AuthServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
public class AuthController {

  private final AuthServiceImpl service;

  @PostMapping("/register")
  public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) throws MessagingException {
    AuthResponse response = service.register(request);
    if (response == null)
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    else
      return ResponseEntity.ok(response);
  }


  @PostMapping("/login")
  public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request) {
    return ResponseEntity.ok(service.authenticate(request));
  }

  @PostMapping("/refresh-token")
  public void refreshToken(
      HttpServletRequest request,
      HttpServletResponse response
  ) throws IOException {
    service.refreshToken(request, response);
  }


}
