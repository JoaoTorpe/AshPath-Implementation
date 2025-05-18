package com.pdsc.ashpath.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pdsc.ashpath.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long>
{
  @Query("SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
  Optional<User> authenticate(String email, String password);

  @Query("SELECT u FROM User u JOIN u.appRoleSet ap WHERE ap.name = 'NECROTOMIST'")
  List<User> findAllNecrotomistUsers();

  @Query("SELECT u FROM User u JOIN u.appRoleSet ap WHERE ap.name = 'ADMIN'")
  List<User> findAllAdminUsers();
}
