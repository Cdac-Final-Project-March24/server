package com.app.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


import com.app.entity.Address;
import com.app.entity.Business;
import com.app.entity.Payment;
import com.app.entity.Status;
import com.app.entity.SubOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
	private Long id;
	
	private LocalDate createdOn;
	
	private String customerName;
	
	private AddBusinessDto business;
	
	private Set<SubOrderDto> subOrder = new HashSet<SubOrderDto>();
	
	private Payment payment;
	
	private Status status;

	private Address address;
	
}
