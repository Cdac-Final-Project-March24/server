package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.OrderService;
import com.app.service.UserService;

@RestController
@RequestMapping("/order")
public class OderController {

	@Autowired
	private OrderService orderService;
}
