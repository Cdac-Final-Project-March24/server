package com.app.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.app.entity.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AddUserDto {
	
	@NotEmpty(message = "User name cannot be blank")
	private String name;
	@NotEmpty(message = "User email cannot be blank")
	private String email;
	@NotEmpty(message = "User password cannot be blank")
	@Min(value = 6, message = "Password length must be minimum 6 characters")
	private String password;
	@NotEmpty(message = "User address cannot be blank")
	private String address;
	@NotEmpty(message = "User contact cannot be blank")
	@Min(value = 10, message = "Contact number must be minimum 10 characters")
	private String mobileNumber;
	@NotEmpty(message = "Role cannot be blank")
	private Role role;

}
