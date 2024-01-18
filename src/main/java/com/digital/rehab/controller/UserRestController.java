package com.digital.rehab.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digital.rehab.entity.User;
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
	public ResponseEntity<String> saveUser(
			@RequestBody User user){
		Long id = userService.saveUser(user);
		String body = "user" + id + "saved";
		
		return ResponseEntity.ok(body);
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserResponse> loginUser(
			@RequestBody UserRequest request){
		
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getUsername(), request.getPassword()));
		
		String token = util.generateToken(request.getUsername());
		return ResponseEntity.ok(new UserResponse(token,"Success"));
	}
	
	@GetMapping("/get/{user_id}")
	public ResponseEntity<User> getUser(
			@PathVariable Long user_id){
		
		User user = userService.getUserById(user_id);
		return ResponseEntity.ok(user);
	}
	
	
}
