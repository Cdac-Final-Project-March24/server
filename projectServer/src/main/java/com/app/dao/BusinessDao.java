package com.app.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entity.Business;
import com.app.entity.OfferingType;

public interface BusinessDao extends JpaRepository<Business, Long> {
	
	@Query("SELECT b FROM Business b WHERE b.owner.address.distance(:latitude, :longitude) <= 10000")
	List<Business> findTopClosest(double latitude, double longitude, Pageable pageable);
}
