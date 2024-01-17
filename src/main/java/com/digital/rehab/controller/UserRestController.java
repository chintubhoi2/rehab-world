package com.digital.rehab.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digital.rehab.entity.Role;
import com.digital.rehab.entity.User;
import com.digital.rehab.model.UserRequest;
import com.digital.rehab.model.UserResponse;
import com.digital.rehab.service.RoleService;
import com.digital.rehab.service.UserService;
import com.digital.rehab.util.JwtUtil;

@RestController
@RequestMapping("/user")
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private JwtUtil util;
	
	@PostMapping("/saveuser")
	public ResponseEntity<String> saveUser(
			@RequestBody User user){
		UUID id = userService.saveUser(user);
		String body = "user" + id + "saved";
		
		return ResponseEntity.ok(body);
	}
	
	@PostMapping("/createrole")
	public ResponseEntity<String> createRole(
			@RequestBody Role role){
		String rolee = roleService.saveRole(role);
		
		String body = "role" + rolee + "saved";
		return ResponseEntity.ok(body);
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserResponse> loginUser(
			@RequestBody UserRequest request){
		
		String token = util.generateToken(request.getEmail());
		return ResponseEntity.ok(new UserResponse(token,"Success"));
	}
	
	
}
