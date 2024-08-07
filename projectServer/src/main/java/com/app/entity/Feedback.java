package com.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@ToString(exclude = {"customer","offering"})
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {
	
	@Column(nullable=false)
	private int rating;
	
	private String review;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="customer_id", nullable = false)
	private User customer;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="offring_id", nullable = false)
	private Offering offering;

}
