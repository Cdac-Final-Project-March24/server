package com.app.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.app.entity.OfferingType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class AddOfferingDto {
	@NotBlank(message = "Name cannot be blank")
	private String name;
	@NotBlank(message = "Description cannot be blank")
	private String description;
	@NotNull(message = "Price cannot be blank")
	private Double price;
	@NotBlank(message = "Functionality cannot be blank")
    private String functionality;
	@NotBlank(message = "Usefulness cannot be blank")
    private String usefulness;
	@NotBlank(message = "benifit cannot be blank")
    private String benifit;
	
	@JsonProperty(access = Access.READ_ONLY)
	private String image;
	
	@JsonProperty(access = Access.READ_ONLY)
	private OfferingType type;
	
}
