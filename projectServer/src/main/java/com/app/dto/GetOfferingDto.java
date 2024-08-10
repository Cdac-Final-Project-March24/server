package com.app.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class GetOfferingDto {
	private String name;
	private String description;
	private double price;
	private String image;
//	private byte[] img;
}
