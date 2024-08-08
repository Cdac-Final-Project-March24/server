package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.PaymentDao;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {
	@Autowired
	PaymentDao paymentDao;

}
