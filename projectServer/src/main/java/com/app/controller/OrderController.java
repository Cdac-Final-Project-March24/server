package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Status;
import com.app.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@GetMapping("/{bId}")
	public ResponseEntity<?> getAllOrders(@PathVariable Long bId){
		return ResponseEntity.status(HttpStatus.OK)
				.body(orderService.getAllOrders(bId));
	}
	
	@PutMapping("/status/{id}")
	public ResponseEntity<?> updateOrderStatus(@PathVariable Long id, @RequestParam Status status){
		System.out.println(status);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(orderService.updateOrderStatus(status, id));
	}
}
