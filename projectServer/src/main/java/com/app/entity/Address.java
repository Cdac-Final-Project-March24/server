package com.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

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
public class Address extends BaseEntity {
	
	@Column(nullable = false, length = 30)
	private String address;
	
	@Column(nullable = false, length = 20)
	private String city;
	
	@Column(nullable = false, length = 20)
	private String state;
	
	@Column(nullable = false, length = 20)
	private String country;
	
	@Column(nullable = false, length = 10)
	private String zip;
	
	private Double longitude;
	
	private Double latitude;
	
	public double distance(double latitude, double longitude) {
		double dLat = Math.toRadians(latitude - this.latitude);
        double dLon = Math.toRadians(longitude - this.longitude);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.cos(Math.toRadians(this.latitude)) * Math.cos(Math.toRadians(latitude)) *
                   Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return 6371000.0 * c;
	}
	
}
