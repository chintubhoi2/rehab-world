package com.digital.rehab.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digital.rehab.entity.Role;
import com.digital.rehab.repository.RoleRepository;
import com.digital.rehab.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepository repo;
	
	@Override
	public String saveRole(Role role) {
		// TODO Auto-generated method stub
		return repo.save(role).getRole();
	}

}
