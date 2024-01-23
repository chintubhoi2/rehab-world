package com.digital.rehab.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Random;
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

import com.digital.rehab.entity.Address;
import com.digital.rehab.entity.Role;
import com.digital.rehab.entity.User;
import com.digital.rehab.exception.ResourceNotFoundException;
import com.digital.rehab.model.AddressDto;
import com.digital.rehab.model.RoleDto;
import com.digital.rehab.model.UserDtoRequest;
import com.digital.rehab.model.UserResponse;
import com.digital.rehab.repository.UserRepository;
import com.digital.rehab.service.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public String saveUser(UserDtoRequest user) {
		User entity = new User();
		BeanUtils.copyProperties(user, entity,"addresses","roles");
		
		List<Address> addresses = user.getAddresses().stream()
	            .map(addressDto -> {
	                Address address = new Address();
	                BeanUtils.copyProperties(addressDto, address);
	                address.setUser(entity);
	                return address;
	            })
	            .collect(Collectors.toList());
	    entity.setAddresses(addresses);
		entity.setPassword(encoder.encode(user.getPassword()));
		entity.setUsername(generateUserName(user.getName()));
		return userRepo.save(entity).getUsername();
	}

	@Override
	public UserResponse getUserById(Long id) {
		// TODO Auto-generated method stub
		User entity = new User();
		Optional<User> op = userRepo.findById(id);
		if (op.isPresent()) {
			entity =  op.get();
			UserResponse user = new UserResponse();
			BeanUtils.copyProperties(entity, user);
			List<AddressDto> AddressDtoes = entity.getAddresses().stream()
		            .map(AddressDtoDto -> {
		                AddressDto AddressDto = new AddressDto();
		                BeanUtils.copyProperties(AddressDtoDto, AddressDto);
		                return AddressDto;
		            })
		            .collect(Collectors.toList());
			user.setAddresses(AddressDtoes);
			List<RoleDto> RoleDtoes = entity.getRoles().stream()
		            .map(RoleDtoDto -> {
		                RoleDto RoleDto = new RoleDto();
		                BeanUtils.copyProperties(RoleDtoDto, RoleDto);
		                return RoleDto;
		            })
		            .collect(Collectors.toList());
			user.setRoles(RoleDtoes);
			return user;
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
		if (entity.isEmpty()) {
			throw new UsernameNotFoundException("user doesnt exist");
		}
		User user = entity.get();
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRole()))
						.collect(Collectors.toList()));
	}

	@Override
	public String deleteByUserId(Long id) {
		// TODO Auto-generated method stub
		User user = userRepo.findById(id).get();
		if (user != null) {
			userRepo.deleteById(id);
			return "Success";
		}
		return null;
	}

	@Override
	public String assignRole(Long user_id, List<RoleDto> roleDtoList) {
		// TODO Auto-generated method stub
		Optional<User> op = userRepo.findById(user_id);
		if (op.isPresent()) {
			User entity = op.get();
			List<Role> roles = roleDtoList.stream().map(roleDto -> {
				Role role = new Role();
				BeanUtils.copyProperties(roleDto, role);
				return role;
			}).collect(Collectors.toList());
			entity.setRoles(roles);
			userRepo.save(entity);
		} else
			throw new ResourceNotFoundException("id " + user_id + " not found");
		return "Success";
	}
	
	public String generateUserName(String userName) {
		
		String CHARACTERS = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();

        String randomString = random.ints(6, 0, CHARACTERS.length())
                .mapToObj(CHARACTERS::charAt)
                .map(Object::toString)
                .collect(Collectors.joining());

        return userName.replace(" ","").toLowerCase() + randomString;
	}

}
