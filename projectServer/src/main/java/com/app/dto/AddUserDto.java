package com.app.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

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
	@Length(min = 6, message = "Password length must be minimum 6 characters")
	private String password;
	
	@NotEmpty(message = "User contact cannot be blank")
	@Min(value = 10, message = "Contact number must be minimum 10 characters")
	private String mobileNumber;

	@NotNull(message = "Role cannot be null")
	private Role role;

	@NotEmpty(message = "User Address cannot be blank")
	private String address;
	
	@NotEmpty(message = "User City cannot be blank")
	private String city;
	
	@NotEmpty(message = "User State cannot be blank")
	private String state;
	
	@NotEmpty(message = "User Country cannot be blank")
	private String country;
	
	@NotEmpty(message = "User Zip cannot be blank")
	private String zip;
	
	@NotNull(message = "Latitude cannot be blank")
	private Double longitude;
	
	@NotNull(message = "Longitude cannot be blank")
	private Double latitude;
}
