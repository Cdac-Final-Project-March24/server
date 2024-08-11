package com.app.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseEntity {
	
	@ManyToOne
	@JoinColumn(name ="user_id", nullable =false)
	private User customer;
	
	@ManyToOne
	@JoinColumn(name ="business_id", nullable =false)
	private Business business;
	
	@OneToMany(mappedBy ="order", cascade = CascadeType.ALL)
	private Set<SubOrder> subOrder = new HashSet<SubOrder>();
	
	@OneToOne
	@JoinColumn(name="payment_id",nullable = false)
	private Payment payment;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false, 
			orphanRemoval = true, cascade = CascadeType.ALL)
	@JoinColumn
	private Address address;
}
