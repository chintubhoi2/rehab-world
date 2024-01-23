package com.digital.rehab.model;

import java.util.List;

import lombok.Data;

@Data
public class UserDtoRequest {
	private String name;
	private String email;
	private String password;
	private Long phone;
	private List<AddressDto> addresses;
}
