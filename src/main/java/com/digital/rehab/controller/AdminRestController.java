package com.digital.rehab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digital.rehab.model.RoleDto;
import com.digital.rehab.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminRestController {

	@Autowired
	private UserService userService;

	@PostMapping("/assign_role/{user_id}")
	public ResponseEntity<?> assignRole(@PathVariable Long user_id, @RequestBody List<RoleDto> roleDtoList) {
		String msg = userService.assignRole(user_id, roleDtoList);
		if(msg!=null) {
			return ResponseEntity.ok("Roles assigned");
		}
		return new ResponseEntity<>("user_id "+user_id+"not found",HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/deleteuser/{user_id}")
	public ResponseEntity<String> delteUser(@PathVariable Long user_id){
		String msg = userService.deleteByUserId(user_id);
		if(msg!=null) {
			return ResponseEntity.ok("user_id "+user_id+"deleted successfully");
		}
		return new ResponseEntity<>("user_id "+user_id+"not found",HttpStatus.NOT_FOUND);
	}
}
