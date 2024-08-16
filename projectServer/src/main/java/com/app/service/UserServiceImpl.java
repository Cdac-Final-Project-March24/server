package com.app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exception.DuplicateException;
import com.app.dao.UserDao;
import com.app.dto.AddUserDto;
import com.app.dto.ApiResponse;
import com.app.dto.UpdateUserRequestDto;
import com.app.entity.Address;
import com.app.entity.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private PasswordEncoder enc;

	@Override
	public ApiResponse addUser(AddUserDto newUser) {
		// Get user details
		User user = mapper.map(newUser, User.class);
		// Get address
		Address address = mapper.map(newUser, Address.class);

		user.setPassword(enc.encode(user.getPassword()));
		user.setAddress(address);
		
		if(userDao.existsByEmail(user.getEmail()))
			throw new DuplicateException("user email Already exists");
		
		userDao.save(user);
		return new ApiResponse("User added Successfully");
	}
	
	
	@Override
	public UpdateUserRequestDto updateUser(String email) {
        User user = userDao.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        
        
        userDao.save(user); // Save the updated user
        return mapper.map(user, UpdateUserRequestDto.class);
    }


	@Override
	public AddUserDto getUserByEmail(String Email) {
		 User user = userDao.findByEmail(Email)
	                .orElseThrow(() -> new RuntimeException("User not found"));
		 AddUserDto dto = mapper.map(user,AddUserDto.class);
		 mapper.map(user.getAddress(), dto);
		 //dto.setAddress(user.getAddress().getAddress());
		 System.out.println(dto);
		 return dto;
	}

}
