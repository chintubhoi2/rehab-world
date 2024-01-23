package com.digital.rehab.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
	
	private String name;
	private String username;
	private String email;
	private String password;
	private Long phone;
	private List<AddressDto> addresses;
	private List<RoleDto> roles;
}
