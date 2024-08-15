package com.app.service;

import java.util.List;

import com.app.dto.ApiResponse;
import com.app.dto.OrderDto;
import com.app.entity.Order;
import com.app.entity.Payment;
import com.app.entity.Status;

public interface OrderService {
	List<OrderDto> getAllOrders(Long bId);
	public ApiResponse updateOrderStatus(Status status, Long id);
	ApiResponse addToCart(String email, Long businessId, Long offeringId);
	ApiResponse placeOrder(Payment payment, Long id);
	OrderDto getCart(String email);
}
