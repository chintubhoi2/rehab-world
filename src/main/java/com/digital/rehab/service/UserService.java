package com.digital.rehab.service;

import java.util.List;
import java.util.Optional;

import com.digital.rehab.entity.User;
import com.digital.rehab.model.RoleDto;
import com.digital.rehab.model.UserDtoRequest;
import com.digital.rehab.model.UserResponse;

public interface UserService {

	String saveUser(UserDtoRequest user);

	UserResponse getUserById(Long id);

	Optional<User> findByUsername(String username);

	String deleteByUserId(Long id);

	String assignRole(Long user_id, List<RoleDto> roleDtoList);
}
