package com.app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entity.Business;
import com.app.entity.Offering;
import com.app.entity.OfferingType;
import com.app.entity.User;

public interface BusinessDao extends JpaRepository<Business, Long> {
	
	@Query("SELECT b FROM Business b WHERE GETDISTANCE(:latitude, :longitude, b.owner.address.latitude, b.owner.address.longitude) <= 30000.00")
	List<Business> findTopClosest(double latitude, double longitude, Pageable pageable);

	Optional<Business> findByOwner(User owner);
	
	List<Business>findAll();
	
	/* List<Business> findByIdIn(List<Long> ids); */

}
