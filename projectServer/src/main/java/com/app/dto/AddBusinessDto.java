package com.app.dto;

import javax.validation.constraints.NotEmpty;

import com.app.entity.Address;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class AddBusinessDto {
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;
	
	@NotEmpty(message = "Business name cannot be blank")
	private String name;
	
	@NotEmpty(message = "Business description cannot be blank")
	private String description;
	
	@JsonProperty(access = Access.READ_ONLY)
	private String cover;
	
	@JsonProperty(access = Access.READ_ONLY)
	private Address address;
}
