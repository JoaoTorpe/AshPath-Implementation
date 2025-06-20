package com.pdsc.ashpath.domain.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pdsc.ashpath.domain.dto.request.user.CreateAdminUserRequest;
import com.pdsc.ashpath.domain.dto.request.user.CreateNecrotomistRequest;
import com.pdsc.ashpath.domain.dto.request.user.CreateViewerUserRequest;
import com.pdsc.ashpath.domain.dto.response.user.AdminUserResponse;
import com.pdsc.ashpath.domain.dto.response.user.NecrotomistUserResponse;
import com.pdsc.ashpath.domain.dto.response.user.UserResponse;
import com.pdsc.ashpath.domain.entity.User;
import com.pdsc.ashpath.domain.enums.UserAppRole;
import com.pdsc.ashpath.repository.AppRoleRepository;
import com.pdsc.ashpath.repository.UserRepository;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final AppRoleRepository appRoleRepository;

  public UserService(UserRepository userRepository, AppRoleRepository appRoleRepository) {
    this.userRepository = userRepository;
    this.appRoleRepository = appRoleRepository;
  }

  public boolean isAdmin(Long userId) {
    Optional<User> optionalUser = userRepository.findById(userId);

    if (optionalUser.isPresent()) {
      User user = optionalUser.get();
      return user.getAppRoleSet().stream()
          .anyMatch(appRole -> appRole.getName().equals(UserAppRole.ADMIN));
    }

    return false;
  }

  // public boolean hasPermission(UserAppRole appRoleToCreate, Long id) {
  //   User user = userRepository.getReferenceById(id);

  //   boolean hasRole = user.getAppRoleSet().stream()
  //       .anyMatch(appRole -> {
  //         var name = appRole.getName();
  //         return name.equals(UserAppRole.ADMIN);
  //       });

  //   return hasRole;
  // }

  public void approveUser(Long userId) {
    Optional<User> optionalUser = userRepository.findById(userId);

    if (optionalUser.isPresent()) {
      User user = optionalUser.get();
      user.setApproved(true);
      user.setLastActivityDate(LocalDateTime.now());
      userRepository.save(user);
    }
  }

  public void rejectUser(Long userId) {
    Optional<User> optionalUser = userRepository.findById(userId);

    if (optionalUser.isPresent()) {
      User user = optionalUser.get();
      user.getAppRoleSet().clear();
      userRepository.delete(user);
    }
  }

  public List<UserResponse> findAllPendingApproval() {
    return this.userRepository.findAllPendingApproval()
        .stream()
        .map((u) -> {
          var ur = new UserResponse();
          ur.setEmail(u.getEmail());
          ur.setFullname(u.getFullname());
          ur.setId(u.getId());
          ur.setRegistrationDate(u.getRegistrationDate());
          ur.setLastActivityDate(u.getLastActivityDate());
          ur.setSpecialization(u.getSpecialization());
          ur.setAppRoleSet(u.getAppRoleSet());
          ur.setApproved(u.getApproved());
          return ur;
        })
        .toList();
  }

  public void createAdminUser(CreateAdminUserRequest adminUser) {
    User user = new User();

    user.setEmail(adminUser.getEmail());
    user.setPassword(adminUser.getPassword());
    user.setFullname(adminUser.getFullname());
    user.setRegistrationDate(LocalDateTime.now());
    user.setLastActivityDate(LocalDateTime.now());
    user.setApproved(false);

    user.addAppRole(appRoleRepository.findByName(UserAppRole.ADMIN).get());

    userRepository.save(user);
  }

  public void createViewerUser(CreateViewerUserRequest viewerUser) {
    User user = new User();

    user.setEmail(viewerUser.getEmail());
    user.setPassword(viewerUser.getPassword());
    user.setFullname(viewerUser.getFullname());
    user.setRegistrationDate(LocalDateTime.now());
    user.setLastActivityDate(LocalDateTime.now());
    user.setApproved(false);

    user.addAppRole(appRoleRepository.findByName(UserAppRole.VIEWER).get());

    userRepository.save(user);
  }

  public void createNecrotomistUser(CreateNecrotomistRequest necrotomistUser) {
    User user = new User();

    user.setEmail(necrotomistUser.getEmail());
    user.setPassword(necrotomistUser.getPassword());
    user.setFullname(necrotomistUser.getFullname());
    user.setRegistrationDate(LocalDateTime.now());
    user.setLastActivityDate(LocalDateTime.now());
    user.setSpecialization(necrotomistUser.getSpecialization());
    user.setApproved(false);

    user.addAppRole(appRoleRepository.findByName(UserAppRole.NECROTOMIST).get());

    userRepository.save(user);
  }

  public UserResponse readUserById(Long userId) {
    Optional<User> optionalUser = userRepository.findById(userId);

    if (optionalUser.isPresent()) {
      User user = optionalUser.get();
      UserResponse userResponse = new UserResponse(user);

      return userResponse;
    }

    return null;
  }

  public List<AdminUserResponse> readAllAdminUsers() {
    List<User> adminUsers;
    List<AdminUserResponse> response;

    adminUsers = userRepository.findAllAdminUsers();
    response = adminUsers
        .stream()
        .map(admin -> new AdminUserResponse(admin))
        .collect(Collectors.toList());

    return response;
  }

  public List<NecrotomistUserResponse> readAllNecrotomistUsers() {
    List<User> necrotomistUsers;
    List<NecrotomistUserResponse> response;

    necrotomistUsers = userRepository.findAllNecrotomistUsers();
    response = necrotomistUsers
        .stream()
        .map(necrotomist -> new NecrotomistUserResponse(necrotomist))
        .collect(Collectors.toList());

    return response;
  }

  public List<NecrotomistUserResponse> filterNecrotomistBySpecialization(String specialization) {
    List<User> necrotomistUsers;
    List<NecrotomistUserResponse> response;

    necrotomistUsers = userRepository.findAllNecrotomistUsersBySpecialization(specialization);
    response = necrotomistUsers
        .stream()
        .map(necrotomist -> new NecrotomistUserResponse(necrotomist))
        .collect(Collectors.toList());

    return response;
  }
}
