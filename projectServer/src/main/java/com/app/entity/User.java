package com.app.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {
	
	@Column(nullable = false, length = 30)
	private String name;
	
	@Column(nullable = false,unique = true, length = 50)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable=false,unique = true, length = 15)
	private String mobileNumber;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private Role role;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OfferingReview> reviews;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false, 
			orphanRemoval = true, cascade = CascadeType.ALL)
	@JoinColumn
	private Address address;
}
