package com.app.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UpdateBusinessDto {
	
	@NotEmpty(message = "Business name cannot be blank")
	private String name;
	@NotEmpty(message = "Business description cannot be blank")
	private String description;
	@NotEmpty(message = "Business email cannot be blank")
	private String email;
	@NotEmpty(message = "Business password cannot be blank")
	@Min(value = 6, message = "Password length must be minimum 6 characters")
	private String password;
	@NotEmpty(message = "Business address cannot be blank")
	private String address;
	@NotEmpty(message = "Business contact cannot be blank")
	@Min(value = 10, message = "Contact number must be minimum 10 characters")
	private String mobileNumber;
	

}
