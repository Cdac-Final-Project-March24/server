package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.User;

public interface UserDao extends JpaRepository<User, Long> {
	boolean existsByEmail(String email);
}

