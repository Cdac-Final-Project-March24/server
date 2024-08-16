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
	public User updateUser(Long id, String name, String email, String password, String mobileNumber, Address address) {
        User user = userDao.findById(id).orElseThrow(() -> new RuntimeException("User not found for id :: " + id));
        
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setMobileNumber(mobileNumber);
        user.setAddress(address);

        return userDao.save(user); // Save the updated user
    }


	@Override
	public User getUserByEmail(String Email) {
		 return userDao.findByEmail(Email)
	                .orElseThrow(() -> new RuntimeException("User not found for email :: " + Email));
	}

}
