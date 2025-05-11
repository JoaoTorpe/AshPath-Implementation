package com.pdsc.ashpath_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pdsc.ashpath_backend.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
