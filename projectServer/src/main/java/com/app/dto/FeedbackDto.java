package com.app.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class FeedbackDto {
	
	
	@NotNull(message = "Rating cannot be blank")
	private Integer rating;
	
	@NotEmpty(message = "Review cannot be blank")
	private String review;
	
	@NotEmpty(message = "Reply cannot be blank")
	private String reply;
	
	@NotEmpty(message = "Customer cannot be null")
	private String customer;
	

}
