package com.pdsc.ashpath.controllers;


import com.pdsc.ashpath.domain.entity.User;
import com.pdsc.ashpath.domain.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pdsc.ashpath.domain.dto.request.LoginRequest;

import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController
{
  private final AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest request)
  {
      User user =  authService.login(request);
      if(Objects.isNull(user))
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha ao fazer login");
      return ResponseEntity.status(HttpStatus.OK).build();
  }


}
