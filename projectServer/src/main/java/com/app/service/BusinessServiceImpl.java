package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.dao.BusinessDao;

public class BusinessServiceImpl implements BusinessService {
	@Autowired
	BusinessDao businessDao;

}
