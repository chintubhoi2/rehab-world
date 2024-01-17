package com.digital.rehab.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.digital.rehab.entity.User;
import com.digital.rehab.repository.UserRepository;
import com.digital.rehab.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	public UUID saveUser(User user) {
		// TODO Auto-generated method stub
		user.setPassword(
				encoder.encode(
						user.getPassword()));
		return repo.save(user).getId();
	}

}
