package com.pdsc.ashpath.domain.service;

import com.pdsc.ashpath.domain.dto.request.LoginRequest;
import com.pdsc.ashpath.domain.entity.User;
import com.pdsc.ashpath.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public User login(LoginRequest request){
        Optional<User> optionalUser = userRepository.authenticate(request.getEmail(), request.getPassword());
        return optionalUser.orElse(null);
    }


}
