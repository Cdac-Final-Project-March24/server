package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Offering;

public interface OfferingDao extends JpaRepository<Offering, Long> {

}
