package com.app.service;

import com.app.dto.AddUserDto;
import com.app.dto.ApiResponse;
import com.app.entity.Address;
import com.app.entity.User;

public interface UserService {
	ApiResponse addUser(AddUserDto newUser);
	User updateUser(Long id, String name, String email, String password, String mobileNumber, Address address) ;
	User getUserByEmail(String Email);
}
