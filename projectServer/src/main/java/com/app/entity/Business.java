package com.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="business")
@Getter
@Setter
@ToString(exclude = {"owner"})
@NoArgsConstructor
@AllArgsConstructor
public class Business extends BaseEntity {
	
	
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String description;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner_id", nullable = false)
	private User owner;

}
