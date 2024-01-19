package com.digital.rehab.model;

import java.util.List;

import lombok.Data;

@Data
public class UserDto {
	private String name;
	private String username;
	private String email;
	private String password;
	private Long phone;
	private List<AddressDto> addresses;
	private List<RoleDto> roles;
}
