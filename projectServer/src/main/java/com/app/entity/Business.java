package com.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(exclude = {"owner"})
@NoArgsConstructor
@AllArgsConstructor
public class Business extends BaseEntity {
	
	@Column(nullable = false, length = 30)
	private String name;
	
	@Column(nullable = false)
	private String description;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner_id", nullable = false)
	private User owner;
	
	private Long orderCount = 0L;
	
	private String cover;
}
