package com.app.service;

import com.app.dto.AddUserDto;
import com.app.dto.ApiResponse;

public interface UserService {
	ApiResponse addUser(AddUserDto newUser);
}
