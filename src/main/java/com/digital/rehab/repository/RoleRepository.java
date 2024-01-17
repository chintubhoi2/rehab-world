package com.digital.rehab.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digital.rehab.entity.Role;

public interface RoleRepository extends JpaRepository<Role, UUID>{

}
