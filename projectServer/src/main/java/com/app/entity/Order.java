package com.app.entity;

import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="orders")
@Getter
@Setter
@ToString(exclude = {"user","payment"})
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseEntity {
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="user_id", nullable =false)
	private User user;
	
	@ManyToMany
	@JoinTable(name="order_offerings",
	joinColumns=@JoinColumn(name="order_id"),
	inverseJoinColumns = @JoinColumn(name="offering_id"))
	private HashMap<Offering, Integer> offerings = new HashMap<Offering, Integer>();
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="payment_id",nullable = false)
	private Payment payment;
	

}
