package com.app.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="offerings")
@Getter
@Setter
@ToString(exclude = {"business"})
@NoArgsConstructor
@AllArgsConstructor
public class Offering extends BaseEntity {
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String description;
	@Column(nullable = false)
	private double price;
	
	private String image;
	@Enumerated(EnumType.STRING)
	private OfferingType type;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "business_id", nullable = false)
	private Business business;
	@OneToMany(mappedBy = "offering",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	List<Feedback> feedbacks = new ArrayList<Feedback>();

}
