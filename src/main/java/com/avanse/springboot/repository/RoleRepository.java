package com.avanse.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avanse.springboot.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
