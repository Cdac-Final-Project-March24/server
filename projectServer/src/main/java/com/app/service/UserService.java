package com.app.service;

import com.app.dto.AddUserDto;
import com.app.dto.ApiResponse;
import com.app.dto.UpdateUserRequestDto;
import com.app.entity.Address;
import com.app.entity.User;

public interface UserService {
	ApiResponse addUser(AddUserDto newUser);
	UpdateUserRequestDto updateUser(String email) ;
	AddUserDto getUserByEmail(String Email);
}
