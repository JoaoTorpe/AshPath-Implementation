package com.pdsc.ashpath.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pdsc.ashpath.domain.entity.User;
import com.pdsc.ashpath.domain.dto.request.LoginRequest;
import com.pdsc.ashpath.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController
{
  private final UserRepository userRepository;

  @PostMapping("/login")
  public ResponseEntity<Void> login(@RequestBody LoginRequest request)
  {
    Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());

    if (optionalUser.isPresent())
    {
      User user = optionalUser.get();
      if (user.getPassword().equals(request.getPassword()))
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
  }
}
