package com.pdsc.ashpath.controllers;

import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pdsc.ashpath.domain.dto.request.user.CreateAdminUserRequest;
import com.pdsc.ashpath.domain.dto.request.user.CreateNecrotomistRequest;
import com.pdsc.ashpath.domain.dto.request.user.CreateViewerUserRequest;
import com.pdsc.ashpath.domain.dto.response.AdminUserResponse;
import com.pdsc.ashpath.domain.dto.response.NecrotomistUserResponse;
import com.pdsc.ashpath.domain.dto.response.UserResponse;
import com.pdsc.ashpath.domain.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController
{
  private final UserService userService;

  public UserController(UserService userService)
  {
    this.userService = userService;
  }

  @PostMapping("/admin")
  public ResponseEntity<Void> createAdminUser(@RequestBody CreateAdminUserRequest request)
  {
    userService.createAdminUser(request);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/viewer")
  public ResponseEntity<Void> createViewerUser(@RequestBody CreateViewerUserRequest request)
  {
    userService.createViewerUser(request);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/necrotomist")
  public ResponseEntity<Void> createNecrotomistUser(@RequestBody CreateNecrotomistRequest request)
  {
    userService.createNecrotomistUser(request);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping("/{userId}")
  public ResponseEntity<UserResponse> readUserById(@PathVariable Long userId)
  {
    UserResponse response = userService.readUserById(userId);

    return Objects.isNull(response) ?
      ResponseEntity.status(HttpStatus.NOT_FOUND).build() :
      ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @GetMapping("/admin")
  public ResponseEntity<List<AdminUserResponse>> readAllAdminUsers()
  {
    List<AdminUserResponse> adminUsers = userService.readAllAdminUsers();
    return ResponseEntity.status(HttpStatus.OK).body(adminUsers);
  }

  @GetMapping("/necrotomist")
  public ResponseEntity<List<NecrotomistUserResponse>> readAllNecrotomistUsers()
  {
    List<NecrotomistUserResponse> necrotomistUsers = userService.readAllNecrotomistUsers();
    return ResponseEntity.status(HttpStatus.OK).body(necrotomistUsers);
  }

  @GetMapping("/necrotomist/{specialization}")
  public ResponseEntity<List<NecrotomistUserResponse>> readAllNecrotomistUsersBySpecialization(@PathVariable String specialization)
  {
    List<NecrotomistUserResponse> necrotomistUsers = userService.filterNecrotomistBySpecialization(specialization);
    return ResponseEntity.status(HttpStatus.OK).body(necrotomistUsers);
  }
}
