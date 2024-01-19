package com.digital.rehab.service;

import java.util.UUID;

import com.digital.rehab.entity.User;

public interface UserService {
	
	UUID saveUser(User user);
}
