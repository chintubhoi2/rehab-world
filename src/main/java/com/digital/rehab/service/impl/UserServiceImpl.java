package com.digital.rehab.service.impl;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.digital.rehab.entity.User;
import com.digital.rehab.model.UserDto;
import com.digital.rehab.repository.AddressRepository;
import com.digital.rehab.repository.UserRepository;
import com.digital.rehab.service.UserService;

@Service
public class UserServiceImpl implements UserService,UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	/*
	 * @Autowired private AddressRepository addressRepo;
	 */

	@Autowired
	private BCryptPasswordEncoder encoder;

	//
	// private BCryptPasswordEncoder encoder;

	@Override
	public Long saveUser(User user) {
		// TODO Auto-generated method stub
		
		user.setPassword(
				encoder.encode(
						user.getPassword()));

		/*
		 * List<Address> addresses = user.getAddresses(); if (addresses != null) { for
		 * (Address address : addresses) { address.setUser(user);
		 * addressRepo.save(address); } }
		 */

		return userRepo.save(user).getId();
	}

	@Override
	public User getUserById(Long id) {
		// TODO Auto-generated method stub
		Optional<User> entity = userRepo.findById(id);
		if (entity.isPresent()) {
			return entity.get();
		}
		return null;
	}

	@Override
	public Optional<User> findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepo.findByUsername(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> entity = findByUsername(username);
		if(entity.isEmpty()) {
			throw new UsernameNotFoundException("user doesnt exist");
		}
		User user = entity.get();
		return new org.springframework.security.core.userdetails.User(
				user.getUsername(),
				user.getPassword(),
				user.getRoles().stream()
				.map(role->new SimpleGrantedAuthority(role.getRole()))
				.collect(Collectors.toList())
				);
	}

}
