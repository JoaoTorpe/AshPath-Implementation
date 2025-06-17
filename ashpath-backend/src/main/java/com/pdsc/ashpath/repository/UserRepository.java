package com.pdsc.ashpath.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pdsc.ashpath.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long>
{
  @Query("SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
  Optional<User> authenticate(String email, String password);

  @Query("SELECT u FROM User u WHERE u.email = :email AND u.password = :password AND u.approved = true")
  Optional<User> authenticateApproved(String email, String password);

  @Query("SELECT u FROM User u JOIN u.appRoleSet ap WHERE ap.name = 'NECROTOMIST'")
  List<User> findAllNecrotomistUsers();

  @Query("SELECT u FROM User u JOIN u.appRoleSet ap WHERE ap.name = 'ADMIN'")
  List<User> findAllAdminUsers();

  @Query("SELECT u FROM User u JOIN u.appRoleSet ap WHERE ap.name = 'NECROTOMIST' AND lower(u.specialization) LIKE lower(concat('%', :specialization, '%'))")
  List<User> findAllNecrotomistUsersBySpecialization(@Param("specialization") String specialization);

  @Query("SELECT u FROM User u JOIN u.appRoleSet ap WHERE u.approved = false")
  List<User> findAllPendingApproval();
}
