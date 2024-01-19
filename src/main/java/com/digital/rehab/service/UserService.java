package com.digital.rehab.service;


import java.util.Optional;

import com.digital.rehab.entity.User;
import com.digital.rehab.model.UserDto;

public interface UserService {
	
	Long saveUser(User user);
	User getUserById(Long id);
	Optional<User>findByUsername(String username);
}
