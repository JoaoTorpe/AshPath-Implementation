package com.pdsc.ashpath.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pdsc.ashpath.domain.entity.AppRole;
import com.pdsc.ashpath.domain.enums.UserAppRole;

public interface AppRoleRepository extends JpaRepository<AppRole, Long>
{
  Optional<AppRole> findByName(UserAppRole roleApp);
}
