package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.dao.PaymentDao;

public class PaymentServiceImpl implements PaymentService {
	@Autowired
	PaymentDao paymentDao;

}
