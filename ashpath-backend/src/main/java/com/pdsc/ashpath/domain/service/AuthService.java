package com.pdsc.ashpath.domain.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pdsc.ashpath.domain.dto.request.LoginRequest;
import com.pdsc.ashpath.domain.entity.User;
import com.pdsc.ashpath.repository.UserRepository;

@Service
public class AuthService
{
  private final UserRepository userRepository;

  public AuthService(UserRepository userRepository)
  {
    this.userRepository = userRepository;
  }

  public User login(LoginRequest request)
  {
    Optional<User> optionalUser = userRepository.authenticate(request.getEmail(), request.getPassword());
    return optionalUser.orElse(null);
  }
}
