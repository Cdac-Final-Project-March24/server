package com.app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exception.DuplicateException;
import com.app.dao.UserDao;
import com.app.dto.AddUserDto;
import com.app.dto.ApiResponse;
import com.app.entity.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;
	@Autowired
	private ModelMapper mapper;

	@Override
	public ApiResponse addUser(AddUserDto newUser) {
		
		User user = mapper.map(newUser, User.class);
		if(userDao.existsByEmail(user.getEmail()))
			throw new DuplicateException("user email Already exists");
		userDao.save(user);
		return new ApiResponse("User added Successfully");
	}
	
	

}
