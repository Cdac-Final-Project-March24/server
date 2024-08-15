package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AddToCartDto;
import com.app.entity.Payment;
import com.app.entity.Status;
import com.app.service.OrderService;

@CrossOrigin
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
//		System.out.println(status);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(orderService.updateOrderStatus(status, id));
	}
	
	@PostMapping("/place-order/{id}")
	public ResponseEntity<?> placeOrder(@RequestBody Payment payment, @PathVariable Long id){
//		String email = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(orderService.placeOrder(payment, id));
	}
	
	@PostMapping("/add-to-cart")
	public ResponseEntity<?> addToCart(@RequestBody AddToCartDto cart){
		String email = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(orderService.addToCart(email, cart.getBusinessId(), cart.getOfferingId()));
	}
	
	@GetMapping("/cart")
	public ResponseEntity<?> getCart(){
		String email = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ResponseEntity.status(HttpStatus.OK)
				.body(orderService.getCart(email));
	}
	
}
