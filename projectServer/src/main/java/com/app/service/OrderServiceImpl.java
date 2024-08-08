package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.dao.OrderDao;

public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderDao orderDao;

}
