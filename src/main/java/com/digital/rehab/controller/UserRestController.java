package com.digital.rehab.controller;


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

import com.digital.rehab.model.LoginRequest;
import com.digital.rehab.model.LoginResponse;
import com.digital.rehab.model.UserDtoRequest;
import com.digital.rehab.model.UserDtoResponse;
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
			@RequestBody UserDtoRequest user){
		
		String userName = userService.saveUser(user);
		String msg = "userName is : " + userName;
		UserDtoResponse response = new UserDtoResponse();
		response.setMessage(msg);
		response.setStaus("OK");
		
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> loginUser(
			@RequestBody LoginRequest request){
		
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getUsername(),
						request.getPassword()));
		
		String token = util.generateToken(request.getUsername());
		return ResponseEntity.ok(new LoginResponse(token,"Success"));
	}
	
	@GetMapping("/get/{user_id}")
	public ResponseEntity<UserResponse> getUser(
			@PathVariable Long user_id){
		
		UserResponse response = userService.getUserById(user_id);
		
		return ResponseEntity.ok(response);
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
