package com.pdsc.ashpath.controllers;


import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pdsc.ashpath.domain.dto.request.LoginRequest;
import com.pdsc.ashpath.domain.dto.response.SuccessfulLoginResponse;
import com.pdsc.ashpath.domain.entity.User;
import com.pdsc.ashpath.domain.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController
{
  private final AuthService authService;

  public AuthController(AuthService authService)
  {
    this.authService = authService;
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request)
  {
    User user = authService.login(request);

    if(Objects.isNull(user)){
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha ao fazer login");
    } 
    else if(!user.getApproved()) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuário inativo");
    }

    SuccessfulLoginResponse response = new SuccessfulLoginResponse(user);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }
}
