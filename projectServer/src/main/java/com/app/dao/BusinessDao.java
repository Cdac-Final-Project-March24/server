package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Business;

public interface BusinessDao extends JpaRepository<Business, Long> {
	
	

}
