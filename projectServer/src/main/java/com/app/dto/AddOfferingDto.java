package com.app.dto;

import javax.validation.constraints.NotBlank;

import com.app.entity.OfferingType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class AddOfferingDto {
	@NotBlank(message = "Name cannot be blank")
	private String name;
	@NotBlank(message = "Description cannot be blank")
	private String description;

	private Double price;
	
	@JsonProperty(access = Access.READ_ONLY)
	private String image;
	
	@JsonProperty(access = Access.READ_ONLY)
	private OfferingType type;
	
}
