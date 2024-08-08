package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.OfferingDao;

@Service
@Transactional
public class OfferingServiceImpl implements OfferingService {
	@Autowired
	OfferingDao offeringDao;

}
