package com.avanse.springboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avanse.springboot.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findUserByEmail(String email);
}
