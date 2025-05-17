package com.pdsc.ashpath.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pdsc.ashpath.domain.entity.User;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long>
{
  @Query("SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
  Optional<User> authenticate(String email,String password);
}
