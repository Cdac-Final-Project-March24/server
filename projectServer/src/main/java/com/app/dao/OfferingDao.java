package com.app.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entity.Offering;
import com.app.entity.OfferingType;

public interface OfferingDao extends JpaRepository<Offering, Long> {
	@Query("SELECT o from Offering o where o.business.id=:id and o.type=:type")
	List<Offering> findByBusinessIdAndType(Long id, OfferingType type, Sort sort);
	
	@Query("SELECT o FROM Offering o where o.type=:type and GETDISTANCE(:latitude, :longitude, o.business.owner.address.latitude, o.business.owner.address.longitude) <= 30000.00")
	List<Offering> findTopClosest(OfferingType type, double latitude, double longitude, Pageable pageable);
	
	
	@Query("SELECT o FROM Offering o WHERE o.business.id = :id AND o.type = :type ORDER BY o.orderCount DESC")
	List<Offering> findMostPreferredOffering(@Param("type") OfferingType type, @Param("id") long id);
	
	List<Offering> findByNameContaining(String name);

}
