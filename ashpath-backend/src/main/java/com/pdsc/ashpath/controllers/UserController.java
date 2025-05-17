package com.pdsc.ashpath.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pdsc.ashpath.domain.entity.AppRole;
import com.pdsc.ashpath.domain.entity.User;
import com.pdsc.ashpath.domain.enums.UserAppRole;
import com.pdsc.ashpath.dto.request.CreateAdminUserRequest;
import com.pdsc.ashpath.dto.request.CreateNecrotomistRequest;
import com.pdsc.ashpath.dto.response.NecrotomistUserResponse;
import com.pdsc.ashpath.repository.AppRoleRepository;
import com.pdsc.ashpath.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController
{
  private final UserRepository userRepository;
  private final AppRoleRepository appRoleRepository;

  @PostMapping("/admin")
  public ResponseEntity<Void> createAdminUser(@RequestBody CreateAdminUserRequest request)
  {
    User user = new User();

    user.setEmail(request.getEmail());
    user.setPassword(request.getPassword());
    user.setFullname(request.getFullname());
    user.setRegistrationDate(LocalDateTime.now());
    user.setLastActivityDate(LocalDateTime.now());

    user.addAppRole(appRoleRepository.findByName(UserAppRole.ADMIN).get());

    userRepository.save(user);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/necrotomist")
  public ResponseEntity<Void> createNecrotomistUser(@RequestBody CreateNecrotomistRequest request)
  {
    User user = new User();

    user.setEmail(request.getEmail());
    user.setPassword(request.getPassword());
    user.setFullname(request.getFullname());
    user.setRegistrationDate(LocalDateTime.now());
    user.setLastActivityDate(LocalDateTime.now());

    user.addAppRole(appRoleRepository.findByName(UserAppRole.NECROTOMIST).get());

    userRepository.save(user);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping("/necrotomist")
  public ResponseEntity<List<NecrotomistUserResponse>> readAllNecrotomistUsers()
  {
    List<User> users;
    List<NecrotomistUserResponse> response;
    AppRole necrotomistAppRole;

    users = userRepository.findAll();
    necrotomistAppRole = appRoleRepository.findByName(UserAppRole.NECROTOMIST).get();

    response = users
      .stream()
      .filter(user -> user.getAppRoleSet().contains(necrotomistAppRole))
      .map(necrotomist -> new NecrotomistUserResponse(necrotomist))
      .collect(Collectors.toList());

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

}
