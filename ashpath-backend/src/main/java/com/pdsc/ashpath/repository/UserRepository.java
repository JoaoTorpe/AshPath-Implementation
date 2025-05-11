package com.pdsc.ashpath.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pdsc.ashpath.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
