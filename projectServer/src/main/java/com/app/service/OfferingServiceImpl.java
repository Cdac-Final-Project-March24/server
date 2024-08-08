package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.dao.OfferingDao;

public class OfferingServiceImpl implements OfferingService {
	@Autowired
	OfferingDao offeringDao;

}
