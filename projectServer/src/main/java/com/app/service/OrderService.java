package com.app.service;

import java.util.List;

import com.app.dto.ApiResponse;
import com.app.entity.Order;
import com.app.entity.Status;

public interface OrderService {
	List<Order> getAllOrders(Long bId);
	public ApiResponse updateOrderStatus(Status status, Long id);
}
