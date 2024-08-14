package com.app.dto;

import javax.validation.constraints.NotNull;

import com.app.entity.PaymentType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PlaceOrderDto {
	private String address;
	private String city;
	private String state;
	private String country;
	private Double longitude;
	private Double latitude;
	@NotNull(message= "Payment type required")
	private PaymentType type;
	@NotNull(message = "Status required")
	private boolean status;
	
	private String upiId;
}
