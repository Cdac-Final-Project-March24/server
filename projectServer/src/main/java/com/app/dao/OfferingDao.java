package com.app.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entity.Offering;
import com.app.entity.OfferingType;

public interface OfferingDao extends JpaRepository<Offering, Long> {
	@Query("SELECT o from Offering o where o.business.id=:id and o.type=:type")
	List<Offering> findByBusinessIdAndType(Long id, OfferingType type, Sort sort);
}
