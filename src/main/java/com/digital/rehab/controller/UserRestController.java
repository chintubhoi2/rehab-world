package com.digital.rehab.controller;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digital.rehab.entity.Address;
import com.digital.rehab.entity.Role;
import com.digital.rehab.entity.User;
import com.digital.rehab.model.UserDto;
import com.digital.rehab.model.UserDtoResponse;
import com.digital.rehab.model.UserRequest;
import com.digital.rehab.model.UserResponse;
import com.digital.rehab.service.UserService;
import com.digital.rehab.util.JwtUtil;

@RestController
@RequestMapping("/user")
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtUtil util;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/saveuser")
	public ResponseEntity<UserDtoResponse> saveUser(
			@RequestBody UserDto user){
		User entity = new User();
		BeanUtils.copyProperties(user, entity,"addresses","roles");
		
		List<Address> addresses = user.getAddresses().stream()
	            .map(addressDto -> {
	                Address address = new Address();
	                BeanUtils.copyProperties(addressDto, address);
	                return address;
	            })
	            .collect(Collectors.toList());
	    entity.setAddresses(addresses);
	    
	    List<Role> roles = user.getRoles().stream()
	            .map(roleDto -> {
	                Role role = new Role();
	                BeanUtils.copyProperties(roleDto, role);
	                return role;
	            })
	            .collect(Collectors.toList());
	    entity.setRoles(roles);
		Long id = userService.saveUser(entity);
		String msg = "user" + id + "saved";
		UserDtoResponse response = new UserDtoResponse();
		response.setMessage(msg);
		response.setStaus("OK");
		
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserResponse> loginUser(
			@RequestBody UserRequest request){
		
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getUsername(),
						request.getPassword()));
		
		String token = util.generateToken(request.getUsername());
		return ResponseEntity.ok(new UserResponse(token,"Success"));
	}
	
	@GetMapping("/get/{user_id}")
	public ResponseEntity<UserDto> getUser(
			@PathVariable Long user_id){
		
		User entity = userService.getUserById(user_id);
		UserDto user = new UserDto();
		BeanUtils.copyProperties(entity, user);
		return ResponseEntity.ok(user);
	}
	@GetMapping("/status")
	public ResponseEntity<?> status(){
		
		return ResponseEntity.ok("Application is running");
	}
	@GetMapping("/status2")
	public ResponseEntity<?> status2(){
		
		return ResponseEntity.ok("Application is running");
	}
	
}
