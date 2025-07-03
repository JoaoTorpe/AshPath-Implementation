package com.pdsc.ashpath.domain.service;

import com.pdsc.ashpath.util.PasswordUtils;
import org.springframework.stereotype.Service;

import com.pdsc.ashpath.domain.dto.request.LoginRequest;
import com.pdsc.ashpath.domain.entity.User;
import com.pdsc.ashpath.repository.UserRepository;

@Service
public class AuthService
{
  private final UserRepository userRepository;
  private final PasswordUtils passwordUtils;
  public AuthService(UserRepository userRepository, PasswordUtils passwordUtils)
  {
    this.userRepository = userRepository;
      this.passwordUtils = passwordUtils;
  }

  public User login(LoginRequest request)
  {
    var optionalUser = userRepository.findByEmail(request.getEmail());
    if(optionalUser.isEmpty())
      return null;

    boolean isValidPassword = passwordUtils.checkPassword(request.getPassword(), optionalUser.get().getPassword());

    return isValidPassword ? optionalUser.get() : null;
  }
}
